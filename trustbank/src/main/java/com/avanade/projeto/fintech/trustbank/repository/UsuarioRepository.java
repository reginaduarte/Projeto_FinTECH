package com.avanade.projeto.fintech.trustbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avanade.projeto.fintech.trustbank.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	// 1) Consulta SQL Nativa - Consulta de usuários
	
	@Query(value = "SELECT ID_USUARIO, NOME, CPF, EMAIL, SENHA, TIPO_USUARIO, \r\n"
			+ " DATA_CRIACAO \r\n"
			+ " FROM USUARIO \r\n", nativeQuery = true)
	List<Usuario> listarUsuarios();
	
	// 2) Consulta SQL Nativa - Consulta de usuários por CPF
	
		@Query(value = "SELECT u.ID_USUARIO, u.NOME, u.CPF, u.EMAIL, u.SENHA, u.TIPO_USUARIO, \r\n"
				+ " u.DATA_CRIACAO \r\n"
				+ " FROM USUARIO u WHERE u.CPF = :valorcpf", nativeQuery = true)
		List<Usuario> listarUsuariosByCpf(@Param("valorcpf") String cpf);
	
	

	
	
}
