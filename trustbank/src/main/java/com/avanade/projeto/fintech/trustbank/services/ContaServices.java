package com.avanade.projeto.fintech.trustbank.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.dto.ExtratoDTO;
import com.avanade.projeto.fintech.trustbank.dto.UsuarioContaDTO;
import com.avanade.projeto.fintech.trustbank.entities.Conta;
import com.avanade.projeto.fintech.trustbank.entities.Transacao;
import com.avanade.projeto.fintech.trustbank.repository.ContaRepository;
import com.avanade.projeto.fintech.trustbank.repository.TransacaoRepository;

@Service
public class ContaServices {

	@Autowired
	private ContaRepository contaRepository;


	
	@Autowired TransacaoRepository transacaoRepository;

	// 1) Método - Criação de conta
	public Conta incluirContas(Conta conta) {
		return contaRepository.save(conta);
	}

	// 2) Consulta Nativa SQL - Consultar todas as contas existentes no sistema
	// (SUGESTÃO FUNÇÃO ADMIN)
	public List<UsuarioContaDTO> listarContas() {
		return contaRepository.listarContas();
	}

	// 3) Consulta Nativa SQL - Consultar todas as contas com CPF
	public List<UsuarioContaDTO> listarContasByCpf(String cpf) {
		return contaRepository.listarContasByCpf(cpf);
	}

	// 4) Consulta Nativa SQL - Extrato, com critério de ID_CONTA
	public List<ExtratoDTO> gerarExtrato(int numConta) {
		return contaRepository.gerarExtrato(numConta);
	}

	// 5) Consulta Nativa SQL - Consulta de saldo por CPF
	public List<Conta> consultarSaldoPorCPF(String cpf) {
		return contaRepository.consultarSaldoPorCPF(cpf);
	}

	// 6) Método para verificar se a conta tem saldo suficiente

	public String verificarSaldoSuficiente(int idConta, BigDecimal valor) {

		// Buscar a conta pelo número da conta para verificar se existe
		Conta conta = contaRepository.findByIdConta(idConta);

		// Verifica se a conta foi encontrada
		if (conta == null) {
			return "Conta não encontrada!";
		}

		// Verifica se o saldo é suficiente para efetuar a transação
		if (conta.getSaldoConta().compareTo(valor) >= 0) {
			return "Saldo suficiente!";
		} else {
			return "Saldo insuficiente! " + "\nSeu saldo é de " + conta.getSaldoConta();
		}
	}

	
	
	
	public void creditarContaDestino(Conta contaDestino, Transacao transferencia) {

		
		int tipoOperacao = 2;	// 2 - CRÉDITO
		int tipoTransacao = transferencia.getTipoTransacao();
		BigDecimal valorCredito = transferencia.getValorTransacao();
		LocalDateTime dataTransacao = transferencia.getDataTransacao();
		String descricaoTransacao = transferencia.getDescricaoTransacao();
		int codOperacao = transferencia.getCodOperacao();
		
		// Adicionando valor na conta destino
		contaDestino.setSaldoConta(valorCredito);
		
		// Registrando transação para que apareça no Extrato da Conta Destiono
		Transacao registroTransfDestino = new Transacao();
		
		registroTransfDestino.setTipoTransacao(tipoTransacao);
		registroTransfDestino.setValorTransacao(valorCredito);
		registroTransfDestino.setDataTransacao(dataTransacao);
		registroTransfDestino.setDescricaoTransacao(descricaoTransacao);
		registroTransfDestino.setConta(contaDestino);
		registroTransfDestino.setTipoOperacao(tipoOperacao);
		registroTransfDestino.setCodOperacao(codOperacao);
		
		
		// Gravar Transação na Conta Destino no Banco de Dados
		 transacaoRepository.save(registroTransfDestino); 
		
  
	}
	

}
