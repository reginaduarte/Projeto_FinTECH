package com.avanade.projeto.fintech.trustbank.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "CONTA")
public class Conta {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONTA", nullable = false)
	private int idConta;
	
	@Column(name = "NUMERO_CONTA")
	private int numeroConta;
	
	@Column(name = "AGENCIA")
	private int numAgencia;
	
	@Column(name = "TIPO_CONTA") // -- 1: SIMPLES, 2: PREMIUM
	private int tipoConta;
	
	@Column(name = "SALDO")
	private BigDecimal saldoConta;
	
	@Column(name = "DATA_ABERTURA")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataAbertura;  
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "ID_USUARIO", nullable = false)  
	private Usuario usuario;
	
	
	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL) // um para muitos (uma conta pode ter várias transações)
	private List<Transacao> transacoes;

	public int getIdConta() {
		return idConta;
	}
	public void setIdConta(int idConta) {
		this.idConta = idConta;
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
	public int getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(int tipoConta) {
		this.tipoConta = tipoConta;
	}
	public BigDecimal getSaldoConta() {
		return saldoConta;
	}
	public void setSaldoConta(BigDecimal saldoConta) {
		this.saldoConta = saldoConta;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
	
	
}
