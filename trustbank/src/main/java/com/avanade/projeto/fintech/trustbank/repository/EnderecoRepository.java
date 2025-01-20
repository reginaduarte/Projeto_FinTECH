package com.avanade.projeto.fintech.trustbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avanade.projeto.fintech.trustbank.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	
	
	// 1) Consulta Nativa SQL - Consulta de usuários e seus endereços
	
	@Query(value = "SELECT \r\n"
			+ " u.NOME, u.CPF, u.EMAIL,\r\n"
			+ " e.CEP, e.LOGRADOURO, e.NUMERO, e.BAIRRO, \r\n"
			+ " e.CIDADE, e.ESTADO, e.UF \r\n"
			+ " FROM USUARIO u INNER JOIN ENDERECO e \r\n"
			+ " ON u.CPF = e.CPF_USUARIO ", nativeQuery = true)
	List<Endereco> listarUsuariosEnderecos();
	
	

}
