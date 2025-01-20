package com.avanade.projeto.fintech.trustbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.projeto.fintech.trustbank.entities.Endereco;
import com.avanade.projeto.fintech.trustbank.services.EnderecoServices;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoServices enderecoServices;

	
	// 1) Lista de endereços
	
	@GetMapping("/lista")
	public ResponseEntity<List<Endereco>> listarEnderecos(){
		return new ResponseEntity<List<Endereco>>(
				enderecoServices.listarEnderecos(), 
				HttpStatus.OK);
	}
	
	// 1) Lista de usuários e endereços
	
	@GetMapping("/listar")
	public ResponseEntity<List<Endereco>> listarUsuariosEnderecos(){
		return new ResponseEntity<List<Endereco>>(
				enderecoServices.listarUsuariosEnderecos(), 
				HttpStatus.OK);
	}
	
}
