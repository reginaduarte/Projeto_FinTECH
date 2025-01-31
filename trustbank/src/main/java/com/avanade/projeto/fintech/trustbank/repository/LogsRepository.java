package com.avanade.projeto.fintech.trustbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avanade.projeto.fintech.trustbank.entities.Logs;

public interface LogsRepository extends JpaRepository<Logs, Integer>{

	// 1) Lista de logs
	
	@Query (value = "\r\n" + "SELECT ID_LOG, COD_ADMIN, ID_USUARIO, "
			+ " TIPO_ALTERACAO, DATA_HORA_ALTERACAO, CAMPO_ALTERADO FROM TABELA_LOGS", nativeQuery = true)
		public List<Logs> listarLogs();
	
	
	}