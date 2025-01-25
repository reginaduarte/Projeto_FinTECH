package com.avanade.projeto.fintech.trustbank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.dto.LoginDTO;
import com.avanade.projeto.fintech.trustbank.entities.Usuario;
import com.avanade.projeto.fintech.trustbank.repository.UsuarioRepository;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
//	public List<Usuario> listarUsuarios(){
//		return usuarioRepository.findAll(); 
//	}
//	
	
	// 1) Consulta de usuários
	public List<Usuario> listarUsuarios(){	
		return usuarioRepository.listarUsuarios();
	}
	
	// 2) Consulta de usuários por CPF
	
	public List<Usuario> listarUsuariosByCpf(String cpf){
		return usuarioRepository.listarUsuariosByCpf(cpf);
	}
	
	// 3) Inclusão / Alteração de usuário
	
	public Usuario incluirUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	
	// Adicionado o método para validar as credenciais de login
	// Método para autenticar
    public Usuario autenticarUsuario(LoginDTO loginRequest) {
        return usuarioRepository.findByNumeroContaAndNumAgenciaAndSenhaUsuario(
                loginRequest.getNumeroConta(),
                loginRequest.getNumAgencia(),
                loginRequest.getSenhaUsuario()
        ).orElse(null);  // Retorna null caso as credenciais sejam inválidas
    }

}
