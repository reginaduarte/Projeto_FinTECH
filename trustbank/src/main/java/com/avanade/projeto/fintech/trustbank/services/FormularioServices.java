package com.avanade.projeto.fintech.trustbank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Formulario;
import com.avanade.projeto.fintech.trustbank.entities.Usuario;
import com.avanade.projeto.fintech.trustbank.repository.FormularioRepository;
import com.avanade.projeto.fintech.trustbank.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class FormularioServices {
	
	@Autowired
	private FormularioRepository formularioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Formulario gravarSolicitacao(Formulario formulario) {
		return formularioRepository.save(formulario);
	}

	public List<Formulario> listarSolicitacoes(){
		return formularioRepository.listarSolicitacoes();
	}
	
	@Transactional
	public Usuario gravarUsuario(String cpf) {
		
		Formulario form = formularioRepository.findByCpf(cpf);
		
		  if (form == null) {
		        throw new RuntimeException("Formulário não encontrado para o CPF: " + cpf);
		    }
		
		// Gravar dados pessoais do Usuário
		
		Usuario usuario = new Usuario();	
		usuario.setNomeUsuario(form.getNome());
		usuario.setEmailUsuario(form.getEmail());
		usuario.setCpfUsuario(form.getCpf());
		usuario.setSenhaUsuario(form.getSenha());
		usuario.setTelefoneUsuario(form.getTelefone());
		usuario.setDataCriacao(form.getDataSolicitacao());
		usuarioRepository.save(usuario);
		
	    // Excluir o formulário após a conversão em usuário
	    formularioRepository.deleteByCpf(cpf);
		
		return usuario;
		
	}
	
	// Método para reprovar a solicitação
	@Transactional
	public void reprovarSolicitacao(String cpf) {
	    Formulario form = formularioRepository.findByCpf(cpf);
	    
	    if (form == null) {
	        throw new RuntimeException("Formulário não encontrado para o CPF: " + cpf);
	    }
	    
	    formularioRepository.deleteByCpf(cpf);
	}

	
}
