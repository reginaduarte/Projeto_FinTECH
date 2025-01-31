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
	
	// Atualização dos dados do usuário pelo ADM
	public Usuario atualizarUsuario(Usuario usuario) {
    return usuarioRepository.save(usuario); 
	}
	public Usuario buscarPorCpf(String cpf) {
	    return usuarioRepository.findByCpfUsuario(cpf);
	}
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

	// Adicionado para exclusão do usuário
	public void deletarUsuario(String cpf) {
	    Usuario usuario = usuarioRepository.findByCpfUsuario(cpf);
	    if (usuario == null) {
	        throw new RuntimeException("Usuário não encontrado");
	    }
	    usuarioRepository.delete(usuario);
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
    
    public Usuario buscarPorId(int idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

 // Método para atualizar os dados do usuário e o tipo da conta
    public Usuario atualizarDados(Usuario usuario) {
        // Verifica se o usuário existe no banco de dados
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getIdUsuario());

        if (usuarioExistente.isPresent()) {
            Usuario usuarioAtualizado = usuarioExistente.get();
            
            // Atualiza os dados do usuário
            usuarioAtualizado.setEmailUsuario(usuario.getEmailUsuario());
            usuarioAtualizado.setTelefoneUsuario(usuario.getTelefoneUsuario());

            // Atualiza o tipo da conta, se informado na requisição
            if (usuario.getConta() != null && usuarioAtualizado.getConta() != null) {
                usuarioAtualizado.getConta().setTipoConta(usuario.getConta().getTipoConta());
            }

            // Salva as alterações no banco de dados
            return usuarioRepository.save(usuarioAtualizado);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

}
