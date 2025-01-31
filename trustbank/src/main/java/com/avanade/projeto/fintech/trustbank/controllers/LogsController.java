package com.avanade.projeto.fintech.trustbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.projeto.fintech.trustbank.entities.Logs;
import com.avanade.projeto.fintech.trustbank.services.LogsServices;

@RestController
@RequestMapping("/logs")
public class LogsController {
	
	@Autowired
	private LogsServices logsService;
	
	// 1) Consulta de logs
	@GetMapping("/lista")
	public ResponseEntity <List<Logs>> listarLogs(){
		return new ResponseEntity<List<Logs>>(
				logsService.listarLogs(), 
				HttpStatus.OK);
	}
	

}
