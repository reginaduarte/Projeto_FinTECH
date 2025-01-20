package com.avanade.projeto.fintech.trustbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.projeto.fintech.trustbank.entities.Boleto;
import com.avanade.projeto.fintech.trustbank.services.BoletoServices;

@RestController
@RequestMapping("/boleto")

public class BoletoController {

	@Autowired
	private BoletoServices boletoService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Boleto>> listarBoletos(){
		
		return new ResponseEntity<List<Boleto>>(
				boletoService.listarBoletos(), 
				HttpStatus.OK);
	}
	
	@PostMapping("/novo")
	public ResponseEntity<?> incluir(@RequestBody Boleto boleto){
		try {
			
			return new ResponseEntity<Boleto>
			(boletoService.incluirBoleto(boleto), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
}