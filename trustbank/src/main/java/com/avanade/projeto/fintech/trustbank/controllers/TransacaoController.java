package com.avanade.projeto.fintech.trustbank.controllers;

import java.math.BigDecimal;
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

import com.avanade.projeto.fintech.trustbank.entities.Transacao;
import com.avanade.projeto.fintech.trustbank.services.TransacaoServices;

@RestController
@RequestMapping("/transacao")

public class TransacaoController {

	@Autowired
	private TransacaoServices transacaoService;
	
	
	// 1 - Listagem das transações
	
	@GetMapping("/lista")
	public ResponseEntity<List<Transacao>> listarTransacoes(){
		
		return new ResponseEntity<List<Transacao>>(
				transacaoService.listarTransacoes(), 
				HttpStatus.OK);
	}
	
	// 2 - Inclusão das transações
	
	@PostMapping("/nova")
	public ResponseEntity<?> incluir(@RequestBody Transacao transacao){
		try {
			
			return new ResponseEntity<Transacao>
			(transacaoService.incluirTransacao(transacao), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
	
	// 3 - Operação da transação
	
	@PostMapping("/{idContaOrigem}/{idContaDestino}/{valor}")
	public ResponseEntity<String> transferir(@PathVariable int idContaOrigem, @PathVariable int idContaDestino, @PathVariable BigDecimal valor){
		
		try {
			transacaoService.transferirValor(idContaOrigem, idContaDestino, valor);
			return ResponseEntity.ok("Transferência realizada com sucesso.");
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro " + e.getMessage());
		}

	}
	
	
}