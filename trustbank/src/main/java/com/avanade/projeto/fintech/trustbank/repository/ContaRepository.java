package com.avanade.projeto.fintech.trustbank.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avanade.projeto.fintech.trustbank.dto.ExtratoDTO;
import com.avanade.projeto.fintech.trustbank.dto.UsuarioContaDTO;
import com.avanade.projeto.fintech.trustbank.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

	// 1) Consulta Nativa SQL - Consultar todas as contas com CPF

	@Query(value = "SELECT \r\n" 
			+ " u.NOME, u.CPF, c.ID_CONTA, \r\n" + " c.NUMERO_CONTA, c.AGENCIA, \r\n"
			+ " CASE c.TIPO_CONTA\r\n" 
			+ " WHEN 1 THEN 'SIMPLES'\r\n" 
			+ " WHEN 2 THEN 'PREMIUM'\r\n"
			+ " END AS TIPO_CONTA,\r\n" 
			+ " c.SALDO, c.DATA_ABERTURA \r\n"
			+ " FROM CONTA c INNER JOIN USUARIO u ON u.ID_USUARIO = c.ID_USUARIO", nativeQuery = true)
	List<UsuarioContaDTO> listarContas();

	// 2) Consulta Nativa SQL - Consultar todas as contas POR CPF

	@Query(value = "SELECT u.NOME, u.CPF, c.ID_CONTA, c.NUMERO_CONTA, c.AGENCIA, c.TIPO_CONTA, c.SALDO, \r\n"
			+ " c.DATA_ABERTURA \r\n"
			+ " FROM CONTA c INNER JOIN USUARIO u ON u.ID_USUARIO = c.ID_USUARIO WHERE u.CPF = :valorcpf", nativeQuery = true)
	List<UsuarioContaDTO> listarContasByCpf(@Param("valorcpf") String cpf);

	// 3) Consulta Nativa SQL - Extrato, com critério de ID_CONTA

	@Query(value = "SELECT \r\n" 
			+ "	c.ID_CONTA, \r\n" 
			+ "	c.NUMERO_CONTA, \r\n" 
			+ "	c.AGENCIA, \r\n"
			+ "	t.ID_TRANSACAO, \r\n" 
			+ "		CASE t.TIPO_TRANSACAO \r\n" 
			+ "			WHEN 1 THEN 'SAQUE' \r\n"
			+ "			WHEN 2 THEN 'DEPÓSITO' \r\n" 
			+ "			WHEN 3 THEN 'PIX' \r\n"
			+ "			WHEN 4 THEN 'BOLETO' \r\n" 
			+ "		END AS TIPO,\r\n" 
			+ "	t.VALOR, \r\n"
			+ "	t.DATA_TRANSACAO,\r\n" 
			+ "	t.DESCRICAO,\r\n" 
			+ "	t.ID_CONTA_DESTINO \r\n"
			+ "	FROM CONTA c INNER JOIN TRANSACAO t \r\n"
			+ "	ON c.ID_CONTA = t.ID_CONTA_ORIGEM WHERE c.ID_CONTA = :valorconta", nativeQuery = true)
	List<ExtratoDTO> gerarExtrato(@Param("valorconta") int numConta);
	

	// 4) Consulta Nativa SQL - Transação
	
	@Modifying
	@Query(value = "UPDATE Conta c SET c.SALDO = c.SALDO - :valor WHERE c.ID_CONTA = :idconta", nativeQuery = true)
	void debitarConta(@Param("idconta") int idConta, @Param("valor") BigDecimal valor);
	
	@Modifying
	@Query(value = "UPDATE Conta c SET c.SALDO = c.SALDO + :valor WHERE c.ID_CONTA = :idconta", nativeQuery = true)
	void creditarConta(@Param("idconta") int idConta, @Param("valor") BigDecimal valor);
	

}
