package com.avanade.projeto.fintech.trustbank.entities;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	private Date dataTransacao;
	
	@Column(name = "DESCRICAO")
	private String descricaoTransacao;
	

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONTA_ORIGEM")
	private Conta contaOrigem;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONTA_DESTINO")
	private Conta contaDestino;
	
	
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
	public Date getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public Conta getContaOrigem() {
		return contaOrigem;
	}
	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	public Conta getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}
	

	
	
}
