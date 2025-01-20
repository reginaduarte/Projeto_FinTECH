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

import com.avanade.projeto.fintech.trustbank.entities.Tarifa;
import com.avanade.projeto.fintech.trustbank.services.TarifaServices;

@RestController
@RequestMapping("/tarifa")

public class TarifaController {

	@Autowired
	private TarifaServices tarifaService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Tarifa>> listarTarifas(){
		
		return new ResponseEntity<List<Tarifa>>(
				tarifaService.listarTarifas(), 
				HttpStatus.OK);
	}
	
	@PostMapping("/nova")
	public ResponseEntity<?> incluir(@RequestBody Tarifa tarifa){
		try {
			
			return new ResponseEntity<Tarifa>
			(tarifaService.incluirTarifa(tarifa), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
}