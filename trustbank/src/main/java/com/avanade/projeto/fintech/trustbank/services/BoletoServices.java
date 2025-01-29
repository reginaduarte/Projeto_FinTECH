package com.avanade.projeto.fintech.trustbank.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Boleto;
import com.avanade.projeto.fintech.trustbank.entities.Conta;
import com.avanade.projeto.fintech.trustbank.entities.Transacao;
import com.avanade.projeto.fintech.trustbank.repository.BoletoRepository;
import com.avanade.projeto.fintech.trustbank.repository.ContaRepository;

@Service
public class BoletoServices {

	@Autowired
	private BoletoRepository boletoRepository;

	@Autowired
	private TransacaoServices transacaoService;

	@Autowired
	private ContaRepository contaRepository;
	

	public List<Boleto> listarBoletos() {
		return boletoRepository.findAll();
	}

	public Boleto incluirBoleto(Boleto boleto) {
		return boletoRepository.save(boleto);
	}

	public Transacao processarBoleto(int idConta, String codigoBoleto, String descricaoTransacao) {

		Transacao transacaoBoleto = new Transacao();

		// Verifica se a conta existe
		Conta conta = contaRepository.findByIdConta(idConta);

		// Verifica se o boleto existe
		Boleto boleto = boletoRepository.findByCodigoBoleto(codigoBoleto);
		
	    // Verifica se o boleto ou o valor do boleto são nulos
	    if (boleto == null || boleto.getValorBoleto() == null) {
	        throw new IllegalArgumentException("O boleto ou o valor do boleto não pode ser nulo");
	    }

//		if (boleto == null) {
//			return "Não foi possível processar! Boleto não existente no sistema.";
//		} 
//		
//		else {

		// CASO BOLETO EXISTA:
		int tipoTransacao = 4; // Colocar como Tipo de Transação 4 = Boleto

		// FAZER A TRANSAÇÃO:
		transacaoBoleto = transacaoService.realizarTransacaoDebitar(
				conta.getIdConta(), 
				boleto.getValorBoleto(),
				tipoTransacao, 
				descricaoTransacao,
				0);

		// ALTERAR STATUS DO BOLETO:
		atualizarStatusBoleto(boleto, transacaoBoleto);

		return transacaoBoleto;

	}

	public void atualizarStatusBoleto(Boleto boleto, Transacao transacaoBoleto) {

		BigDecimal valorBoleto = boleto.getValorBoleto();
		
		// Se valor da transação é maior ou igual ao valor do Boleto
		if (transacaoBoleto.getValorTransacao().compareTo(valorBoleto) >= 0) {
			boleto.setStatusBoleto(2); // 2 - Pago total
		} else if (transacaoBoleto.getValorTransacao().compareTo(valorBoleto) < 0) {
			boleto.setStatusBoleto(1); // 1 - Pago parcial
		} else {
			boleto.setStatusBoleto(0); // 0 - Não pago, caso nenhum pagamento seja realizado
		}
		
		// Atualizar a coluna de ID_TRANSACAO em BOLETO
		
		boleto.setTransacao(transacaoBoleto);
		
		
		// Gravar Status_Boleto no Banco de Dados
		boletoRepository.save(boleto);


		
	}
	
	// Método para buscar a data de vencimento do boleto pelo código
	public LocalDateTime consultarVencimentoPorCodigoBoleto(String codigoBoleto) {
	    // Buscar o boleto pelo código
	    Boleto boleto = boletoRepository.findByCodigoBoleto(codigoBoleto);

	    // Verificar se o boleto existe
	    if (boleto == null) {
	        throw new IllegalArgumentException("Boleto não encontrado!");
	    }

	    // Verificar se o campo dataVencimento é null
	    if (boleto.getDataVencimento() == null) {
	        throw new IllegalArgumentException("Data de vencimento do boleto não encontrada!");
	    }

	    // Converter Date para LocalDateTime
	    return boleto.getDataVencimento().toInstant()
	                 .atZone(ZoneId.systemDefault())
	                 .toLocalDateTime();
	}
	
	
	// Método para buscar boleto pelo código
    public Boleto buscarBoletoPorCodigo(String codigoBoleto) {
        // Aqui você precisa buscar o boleto no banco de dados com base no código do boleto
        return boletoRepository.findByCodigoBoleto(codigoBoleto); // Substitua pelo método correto no seu repositório
    }


}

//	}
