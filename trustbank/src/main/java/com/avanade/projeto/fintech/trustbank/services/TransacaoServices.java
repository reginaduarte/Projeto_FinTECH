package com.avanade.projeto.fintech.trustbank.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Conta;
import com.avanade.projeto.fintech.trustbank.entities.Transacao;
import com.avanade.projeto.fintech.trustbank.repository.ContaRepository;
import com.avanade.projeto.fintech.trustbank.repository.TransacaoRepository;

import jakarta.transaction.Transactional;


@Service
public class TransacaoServices {

	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	
	// 1) Listar as transações
	
	public List<Transacao> listarTransacoes(){
		return transacaoRepository.findAll(); 
	}
	
	// 2) Incluir as transações
	
	public Transacao incluirTransacao(Transacao transacao) {
		return transacaoRepository.save(transacao);
	}
	
	// 3) Transação

		@Transactional
		public void transferirValor(int idContaOrigem, int idContaDestino, BigDecimal valor) {

			// 1. Verificar se as contas são encontradas

			Conta contaOrigem = contaRepository.findById(idContaOrigem)
					.orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));

			Conta contaDestino = contaRepository.findById(idContaDestino)
					.orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

			// 2. Verifica se há saldo suficiente na conta de origem

			if (contaOrigem.getSaldoConta().compareTo(valor) < 0) {
				throw new RuntimeException("Saldo insuficiente na conta de origem");
			}

			// 3. Realiza o débito na conta de origem
			
			contaRepository.debitarConta(idContaOrigem, valor);

			// 4. Realiza o crédito na conta de destino
			contaRepository.creditarConta(idContaDestino, valor);

			// 5. Registro da transação
			
	        Transacao transacao = new Transacao();
	        transacao.setValorTransacao(valor);
	        transacao.setContaOrigem(contaOrigem);
	        transacao.setContaDestino(contaDestino);

	        transacaoRepository.save(transacao);
	
	        
		}
}
