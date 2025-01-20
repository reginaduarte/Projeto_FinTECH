package com.avanade.projeto.fintech.trustbank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.dto.ExtratoDTO;
import com.avanade.projeto.fintech.trustbank.dto.UsuarioContaDTO;
import com.avanade.projeto.fintech.trustbank.entities.Conta;
import com.avanade.projeto.fintech.trustbank.repository.ContaRepository;

@Service
public class ContaServices {

	@Autowired
	private ContaRepository contaRepository;


	public Conta incluirContas(Conta conta) {
		return contaRepository.save(conta);
	}

	// 1) Consulta Nativa SQL - Consultar todas as contas
	public List<UsuarioContaDTO> listarContas() {
		return contaRepository.listarContas();
	}

	// 2) Consulta Nativa SQL - Consultar todas as contas com CPF
	public List<UsuarioContaDTO> listarContasByCpf(String cpf) {
		return contaRepository.listarContasByCpf(cpf);
	}

	// 3) Consulta Nativa SQL - Extrato, com critério de ID_CONTA
	public List<ExtratoDTO> gerarExtrato(int numConta) {
		return contaRepository.gerarExtrato(numConta);
	}

	
		
		
	}


