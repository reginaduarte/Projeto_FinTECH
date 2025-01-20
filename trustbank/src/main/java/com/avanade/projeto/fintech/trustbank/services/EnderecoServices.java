package com.avanade.projeto.fintech.trustbank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Endereco;
import com.avanade.projeto.fintech.trustbank.repository.EnderecoRepository;

@Service
public class EnderecoServices {
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Endereco> listarEnderecos(){
		return enderecoRepository.findAll(); 
	}
	
	public List<Endereco> listarUsuariosEnderecos(){
		return enderecoRepository.listarUsuariosEnderecos(); 
	}
	
}
