package com.avanade.projeto.fintech.trustbank.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "TABELA_LOGS")
public class Logs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LOG")
	private int idLog;

	@Column(name = "COD_ADMIN")
	private String codAdmin;
	
	@Column(name = "ID_USUARIO")
	private int idCliente;
	
	@Column(name = "TIPO_ALTERACAO")
	private String tipoAlteracao;
	
	@Column(name = "DATA_HORA_ALTERACAO")
	@Temporal(value = TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataHoraAlteracao;

	@Column(name = "CAMPO_ALTERADO")
	private String campoAlterado;
	
	public String getCodAdmin() {
		return codAdmin;
	}

	public void setCodAdmin(String codAdmin) {
		this.codAdmin = codAdmin;
	}

	public String getTipoAlteracao() {
		return tipoAlteracao;
	}

	public void setTipoAlteracao(String tipoAlteracao) {
		this.tipoAlteracao = tipoAlteracao;
	}

	public Date getDataHoraAlteracao() {
		return dataHoraAlteracao;
	}

	public void setDataHoraAlteracao(Date dataHoraAlteracao) {
		this.dataHoraAlteracao = dataHoraAlteracao;
	}

	public int getIdLog() {
		return idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getCampoAlterado() {
		return campoAlterado;
	}

	public void setCampoAlterado(String campoAlterado) {
		this.campoAlterado = campoAlterado;
	}
		
}
