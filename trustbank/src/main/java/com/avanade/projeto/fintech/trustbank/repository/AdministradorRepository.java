package com.avanade.projeto.fintech.trustbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanade.projeto.fintech.trustbank.entities.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{
	
	// Adicionado para buscar email e senha
	Optional<Administrador> findByEmailUsuarioAndSenhaUsuario(String emailUsuario, String senhaUsuario);
	
}
