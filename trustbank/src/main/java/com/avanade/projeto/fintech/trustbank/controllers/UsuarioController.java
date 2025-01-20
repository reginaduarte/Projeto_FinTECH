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

import com.avanade.projeto.fintech.trustbank.entities.Usuario;
import com.avanade.projeto.fintech.trustbank.services.UsuarioServices;

@RestController
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	private UsuarioServices usuarioService;

	// 1) Consulta de usuários
	@GetMapping("/lista")
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return new ResponseEntity<List<Usuario>>(usuarioService.listarUsuarios(), HttpStatus.OK);
	}

	// 2) Consulta de usuários por CPF
	@GetMapping("/{cpf}")
	public ResponseEntity<List<Usuario>> listarUsuariosByCpf(@PathVariable("cpf") String cpf) {
		return new ResponseEntity<List<Usuario>>(usuarioService.listarUsuariosByCpf(cpf), HttpStatus.OK);
	}

	
	// 3) Inclusão / Alteração de usuário
	@PostMapping("/novo")
	public ResponseEntity<?> incluir(@RequestBody Usuario usuario) {
		try {
			return new ResponseEntity<Usuario>(usuarioService.incluirUsuario(usuario), HttpStatus.CREATED);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}
	}

}