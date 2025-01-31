package com.avanade.projeto.fintech.trustbank.dto;

import java.math.BigDecimal;
import java.util.Date;

public class UsuarioContaDTO {

	private int idUsuario;
	private String nomeUsuario;
	private String cpfUsuario;
	private String telefoneUsuario;
	private String emailUsuario;
	private int idConta;
	private int numeroConta;
	private int numAgencia;
	private String tipoConta; // -- 1: SIMPLES, 2: PREMIUM
	private BigDecimal saldoConta;
	private Date dataAbertura;
	
	public UsuarioContaDTO(int idUsuario, String nomeUsuario, String cpfUsuario, String telefoneUsuario, String emailUsuario, int idConta, int numeroConta, int numAgencia,
			String tipoConta, BigDecimal saldoConta, Date dataAbertura) {
		super();
		this.setIdUsuario(idUsuario);
		this.setNomeUsuario(nomeUsuario);	
		this.setCpfUsuario(cpfUsuario);
		this.setTelefoneUsuario(telefoneUsuario);
		this.setEmailUsuario(emailUsuario);
		this.setIdConta(idConta);
		this.setNumeroConta(numeroConta);
		this.setNumAgencia(numAgencia);
		this.setTipoConta(tipoConta);
		this.setSaldoConta(saldoConta);
		this.setDataAbertura(dataAbertura);
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getCpfUsuario() {
		return cpfUsuario;
	}
	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public int getNumAgencia() {
		return numAgencia;
	}

	public void setNumAgencia(int numAgencia) {
		this.numAgencia = numAgencia;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public BigDecimal getSaldoConta() {
		return saldoConta;
	}

	public void setSaldoConta(BigDecimal saldoConta) {
		this.saldoConta = saldoConta;
	}

	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}
	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
	
	
	
	
	
}
