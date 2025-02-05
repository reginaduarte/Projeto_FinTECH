package com.avanade.projeto.fintech.trustbank.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.projeto.fintech.trustbank.dto.LoginDTO;
import com.avanade.projeto.fintech.trustbank.dto.UsuarioContaDTO;
import com.avanade.projeto.fintech.trustbank.entities.Usuario;
import com.avanade.projeto.fintech.trustbank.services.FormularioServices;
import com.avanade.projeto.fintech.trustbank.services.UsuarioServices;

@RestController
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	private UsuarioServices usuarioService;
	
	@Autowired
	private FormularioServices formularioServices;

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
	
	// Adicionado o método para excluir o cliente
	@DeleteMapping("/deletar/{cpf}")
	public ResponseEntity<?> deletarUsuario(@PathVariable("cpf") String cpf) {
	    try {
	        usuarioService.deletarUsuario(cpf);
	        return ResponseEntity.ok("Usuário deletado com sucesso!");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
	    }
	}
	
	// Adicionado o método para autenticação do login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
        Usuario usuario = usuarioService.autenticarUsuario(loginRequest);
        
        if (usuario != null) {
            // Construir o DTO para retornar as informações
            UsuarioContaDTO usuarioContaDTO = new UsuarioContaDTO(
            	usuario.getIdUsuario(),
                usuario.getNomeUsuario(),
                usuario.getCpfUsuario(),
                usuario.getTelefoneUsuario(),
                usuario.getEmailUsuario(),
                usuario.getConta().getIdConta(),  // Acessando a conta vinculada ao usuário
                usuario.getConta().getNumeroConta(),
                usuario.getConta().getNumAgencia(),
                usuario.getConta().getTipoConta() == 1 ? "SIMPLES" : "PREMIUM", // Se necessário converter para string
                usuario.getConta().getSaldoConta(),
                usuario.getConta().getDataAbertura()
            );

            return ResponseEntity.ok(usuarioContaDTO); // Retorna o DTO com as informações
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
        }
    }
	
	// Adicionado o método para consulta do saldo
	@GetMapping("/{idConta}/saldo")
	public ResponseEntity<?> consultarSaldo(@PathVariable("idConta") int idConta) {
	    try {
	        BigDecimal saldo = usuarioService.consultarSaldo(idConta);
	        return ResponseEntity.ok(saldo);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada!");
	    }
	}
	
	@PutMapping("/atualizar/{idUsuario}")
	public ResponseEntity<?> atualizarUsuario(@PathVariable int idUsuario, @RequestBody Usuario usuario) {
	    try {
	        Usuario usuarioExistente = usuarioService.buscarPorId(idUsuario);
	        if (usuarioExistente == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
	        }
	        
	        // Atualizar os dados do usuário
	        usuarioExistente.setNomeUsuario(usuario.getNomeUsuario());
	        usuarioExistente.setCpfUsuario(usuario.getCpfUsuario());
	        usuarioExistente.setEmailUsuario(usuario.getEmailUsuario());
	        usuarioExistente.setTelefoneUsuario(usuario.getTelefoneUsuario());

	        if (usuarioExistente.getConta() != null) {
	            usuarioExistente.getConta().setUsuario(usuarioExistente);
	        }

	        Usuario usuarioAtualizado = usuarioService.salvar(usuarioExistente);

	        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar usuário");
	    }
	}


	
	 //Adicionado o método para atualização de dados
	@PutMapping("/atualizar")
	public ResponseEntity<?> atualizarDados(@RequestBody Usuario usuario) {
		try {
			usuarioService.atualizarDados(usuario);
	        return ResponseEntity.ok("Dados atualizados com sucesso!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar dados.");
	        }
	    }
	
	
	// 3) Inclusão / Alteração de usuário - form
	@PostMapping("/new/{cpf}")
	public ResponseEntity<?> gravarUsuario(@PathVariable("cpf") String cpf) {
		try {
			return new ResponseEntity<Usuario>(formularioServices.gravarUsuario(cpf), HttpStatus.CREATED);

		} catch (Exception e) {

			
			 throw new RuntimeException(e);
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}
	}
	
	// 4) Exclusão de usuário - form
	@DeleteMapping("/reprovar/{cpf}")
	public ResponseEntity<?> reprovarSolicitacao(@PathVariable("cpf") String cpf) {
	    try {
	        formularioServices.reprovarSolicitacao(cpf);
	        return ResponseEntity.ok("Solicitação reprovada com sucesso!");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao reprovar a solicitação: " + e.getMessage());
	    }
	}


}
