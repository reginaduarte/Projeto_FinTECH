package com.avanade.projeto.fintech.trustbank.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
    public Usuario autenticarUsuario(LoginDTO loginRequest) {
        return usuarioRepository.findByNumeroContaAndNumAgenciaAndSenhaUsuario(
                loginRequest.getNumeroConta(),
                loginRequest.getNumAgencia(),
                loginRequest.getSenhaUsuario()
        ).orElse(null);  // Retorna null caso as credenciais sejam inválidas
    }
    
    // Adicionado o método para consultar o saldo
    public BigDecimal consultarSaldo(int idConta) {
        return usuarioRepository.findSaldoByIdConta(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
    }
    
 // Método para atualizar os dados do usuário
    public Usuario atualizarDados(Usuario usuario) {
        // Verifica se o usuário existe
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getIdUsuario());
        if (usuarioExistente.isPresent()) {
            Usuario usuarioAtualizado = usuarioExistente.get();
            usuarioAtualizado.setEmailUsuario(usuario.getEmailUsuario());  // Atualiza email
            usuarioAtualizado.setTelefoneUsuario(usuario.getTelefoneUsuario());  // Atualiza telefone
            return usuarioRepository.save(usuarioAtualizado);  // Salva a alteração
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }


}
