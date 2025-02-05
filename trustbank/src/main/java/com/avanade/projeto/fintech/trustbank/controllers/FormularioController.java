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

import com.avanade.projeto.fintech.trustbank.entities.Formulario;
import com.avanade.projeto.fintech.trustbank.services.FormularioServices;

@RestController
@RequestMapping("/form")
public class FormularioController {

	@Autowired
	private FormularioServices formularioServices;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Formulario>> listarSolicitacoes() {
		return new ResponseEntity<List<Formulario>>(formularioServices.listarSolicitacoes(), HttpStatus.OK);
	}

	// Inclusão
	
		@PostMapping("/solicitar")
		public ResponseEntity<?> gravar(@RequestBody Formulario formulario) {
			try {
				return new ResponseEntity<Formulario>(formularioServices.gravarSolicitacao(formulario), 
						HttpStatus.CREATED);

			} catch (Exception e) {

				 throw new RuntimeException(e);
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

			}
		}
		
	
}
