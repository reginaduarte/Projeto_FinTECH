package com.avanade.projeto.fintech.trustbank.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Conta;
import com.avanade.projeto.fintech.trustbank.entities.Pix;
import com.avanade.projeto.fintech.trustbank.entities.Transacao;
import com.avanade.projeto.fintech.trustbank.repository.ContaRepository;
import com.avanade.projeto.fintech.trustbank.repository.PixRepository;


@Service
public class PixServices {

    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private TransacaoServices transacaoService;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContaServices contaServices;

    
    public List<Pix> listarPixes() {
        return pixRepository.findAll();
    }
    
    public Pix incluirPix(Pix pix) {
        // Salvar o PIX no banco de dados
        return pixRepository.save(pix);
    }
    
    public Transacao processarPix(int idContaOrigem, int idContaDestino, BigDecimal valor, String chavePix, String descricaoTransacao) {
        // Buscar a conta de origem e destino diretamente pelo ID
        Conta contaOrigem = contaRepository.findByIdConta(idContaOrigem);
        if (contaOrigem == null) {
            throw new RuntimeException("Conta de origem não encontrada");
        }

        Conta contaDestino = contaRepository.findByIdConta(idContaDestino);
        if (contaDestino == null) {
            throw new RuntimeException("Conta de destino não encontrada");
        }

//         //Verificar se a chave PIX existe e está associada a uma transação
//        Pix pix = pixRepository.findByChavePix(chavePix);
//        if (pix == null || pix.getTransacao() == null) {
//            throw new RuntimeException("Chave PIX não encontrada ou sem transação associada");
//        }
        
        Pix pix = pixRepository.findByChavePix(chavePix);
        if (pix == null) {
          throw new RuntimeException("Chave PIX não encontrada ou sem transação associada");
      }

        // Obter o valor da transação PIX
//        BigDecimal valorPix = pix.getTransacao().getValorTransacao();
        
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor da transação PIX inválido");
        }

        // 1. Realizar o débito na conta de origem
        // A função 'realizarTransacaoDebitar' debita a conta de origem
        Transacao transacaoDebito = transacaoService.realizarTransacaoDebitar(
                contaOrigem.getIdConta(),
                valor,
                3, // Tipo de Transação PIX (supondo que 3 é para PIX)
                descricaoTransacao,
                0
        );

        // 2. Creditar na conta de destino
        // A função 'transferirParaOutraConta' deve fazer a transação de crédito na conta de destino
        contaServices.creditarContaDestino(contaDestino, transacaoDebito);

        // Associar a conta de origem ao PIX
        pix.setContaOrigem(contaOrigem); 

        // Associar a conta de destino ao PIX
        pix.setTransacao(transacaoDebito);  // Associando a transação ao PIX para manter o histórico

        // Salvar a transação de PIX com as contas associadas
        pixRepository.save(pix);

        return transacaoDebito;
    }
}


