package com.avanade.projeto.fintech.trustbank.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.projeto.fintech.trustbank.entities.Administrador;
import com.avanade.projeto.fintech.trustbank.services.AdministradorServices;


@RestController
@RequestMapping("/admin")

public class AdministradorController {

	@Autowired
	private AdministradorServices adminService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Administrador>> listarAdmins(){
		
		return new ResponseEntity<List<Administrador>>(
				adminService.listarAdmins(), 
				HttpStatus.OK);
	}
	
	// Adicionado o método para autenticação do login
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody Administrador loginRequest) {
//	    Administrador admin = adminService.validarCredenciais(loginRequest.getEmailUsuario(), loginRequest.getSenhaUsuario());
//	    if (admin != null) {
//	        return ResponseEntity.ok("Login realizado com sucesso!"); 
//	    } else {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
//	    }
//	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Administrador loginRequest) {
	    Administrador admin = adminService.validarCredenciais(loginRequest.getEmailUsuario(), loginRequest.getSenhaUsuario());
	    
	    if (admin != null) {
	        // Criar um objeto de resposta com o ID e o nome do administrador
	        Map<String, Object> response = new HashMap<>();
	        response.put("idAdmin", admin.getIdAdmin());
	        response.put("nomeUsuario", admin.getNomeUsuario());

	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
	    }
	}

	
	
	@PostMapping("/novo")
	public ResponseEntity<?> incluir(@RequestBody Administrador admin){
		try {
			
			return new ResponseEntity<Administrador>
			(adminService.incluirAdmin(admin), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
	
	// Adicionar o Put e Delete
}