package com.avanade.projeto.fintech.trustbank.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "TRANSACAO")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TRANSACAO")
	private int idTransacao;
	
	@Column(name = "TIPO_TRANSACAO")
	private int tipoTransacao; // -- 0:  TRANSFERÊNCIA SUJEITA A TARIFAS , 1:SAQUE , 2:DEPÓSITO , 3:PIX , 4:BOLETO
	
	@Column(name = "VALOR")
	private BigDecimal valorTransacao;
	
	@Column(name = "DATA_TRANSACAO")
	@Temporal(value = TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataTransacao;
	
	@Column(name = "DESCRICAO")
	private String descricaoTransacao;

	@ManyToOne // Muitos para um - Uma transação está associada a uma conta
    @JoinColumn(name = "ID_CONTA", nullable = false)  
    private Conta conta;
	
	@Column(name = "COD_OPERACAO")
	private Integer codOperacao;
	
	@Column(name = "TIPO_OPERACAO")
	private Integer tipoOperacao;
	
	
	public int getIdTransacao() {
		return idTransacao;
	}
	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
	}
	public int getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(int tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	public BigDecimal getValorTransacao() {
		return valorTransacao;
	}
	public void setValorTransacao(BigDecimal valorTransacao) {
		this.valorTransacao = valorTransacao;
	}
	public String getDescricaoTransacao() {
		return descricaoTransacao;
	}
	public void setDescricaoTransacao(String descricaoTransacao) {
		this.descricaoTransacao = descricaoTransacao;
	}
	public LocalDateTime getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(LocalDateTime dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Integer getCodOperacao() {
		return codOperacao;
	}
	public void setCodOperacao(Integer codOperacao) {
		this.codOperacao = codOperacao;
	}
	public Integer getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(Integer tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	


	
	
	
}
