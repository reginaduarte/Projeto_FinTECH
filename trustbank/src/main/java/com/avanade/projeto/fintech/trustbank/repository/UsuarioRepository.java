package com.avanade.projeto.fintech.trustbank.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avanade.projeto.fintech.trustbank.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	// 1) Consulta SQL Nativa - Consulta de usuários
	
	@Query(value = "SELECT ID_USUARIO, NOME, CPF, EMAIL, SENHA, TIPO_USUARIO, TELEFONE, \r\n"
			+ " DATA_CRIACAO \r\n"
			+ " FROM USUARIO WHERE TIPO_USUARIO = 2 \r\n", nativeQuery = true)//Para listar apenas os clientes
	List<Usuario> listarUsuarios();
	
	// 2) Consulta SQL Nativa - Consulta de usuários por CPF
	
		@Query(value = "SELECT u.ID_USUARIO, u.NOME, u.CPF, u.EMAIL, u.SENHA, u.TIPO_USUARIO, u.TELEFONE, \r\n"
				+ " u.DATA_CRIACAO \r\n"
				+ " FROM USUARIO u WHERE u.CPF = :valorcpf", nativeQuery = true)
		List<Usuario> listarUsuariosByCpf(@Param("valorcpf") String cpf);
		
		
	// Adicionado para buscar email e senha
	@Query("SELECT u FROM Usuario u WHERE u.conta.numeroConta = :numeroConta AND u.conta.numAgencia = :numAgencia AND u.senhaUsuario = :senhaUsuario")
	Optional<Usuario> findByNumeroContaAndNumAgenciaAndSenhaUsuario(int numeroConta, int numAgencia, String senhaUsuario);

	// Adicionado para buscar o saldo a partir do idConta
	@Query("SELECT c.saldoConta FROM Conta c WHERE c.idConta = :idConta")
	Optional<BigDecimal> findSaldoByIdConta(@Param("idConta") int idConta);
	
	// Adicionado para buscar usuários por CPF
	Usuario findByCpfUsuario(String cpf);
	
}
