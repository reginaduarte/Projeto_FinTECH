package com.avanade.projeto.fintech.trustbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.projeto.fintech.trustbank.dto.ExtratoDTO;
import com.avanade.projeto.fintech.trustbank.dto.UsuarioContaDTO;
import com.avanade.projeto.fintech.trustbank.entities.Conta;
import com.avanade.projeto.fintech.trustbank.services.ContaServices;


@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaServices contaService;
	
	// 1) Consulta Nativa SQL - Consultar todas as contas com CPF
	@GetMapping("/lista")
	public ResponseEntity<List<UsuarioContaDTO>> listarContas(){
		return new ResponseEntity<List<UsuarioContaDTO>>(
				contaService.listarContas(), 
				HttpStatus.OK);
	}
	
		// 2) Consulta Nativa SQL - Consultar todas as contas POR CPF
		@GetMapping("/{cpf}")
		public ResponseEntity<List<UsuarioContaDTO>> listarContasByCpf(@PathVariable("cpf") String cpf){
			return new ResponseEntity<List<UsuarioContaDTO>>(
					contaService.listarContasByCpf(cpf), 
					HttpStatus.OK);
		}
	
		
		// 3) Consulta Nativa SQL - Extrato, com critério de ID_CONTA
		
		@GetMapping("/extrato/{numConta}")
		public ResponseEntity<List<ExtratoDTO>> gerarExtrato(@PathVariable("numConta") int numConta){
			return new ResponseEntity<List<ExtratoDTO>>(
					contaService.gerarExtrato(numConta), 
					HttpStatus.OK);
		}
		
		

		// 4) Consulta Nativa SQL - INSERT DE CONTAS
	
		@PostMapping("/nova")
		public ResponseEntity<?> incluir(@RequestBody Conta conta){
		try {
			return new ResponseEntity<Conta>
			(contaService.incluirContas(conta), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	

	
}
