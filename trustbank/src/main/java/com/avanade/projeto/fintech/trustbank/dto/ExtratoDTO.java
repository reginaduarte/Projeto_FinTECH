package com.avanade.projeto.fintech.trustbank.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ExtratoDTO {

	private int idConta;
	private int numeroConta;
	private int numAgencia;
	private int idTransacao;
	private String tipoTransacao;  // -- 0:  TRANSFERÊNCIA SUJEITA A TARIFAS , 1:SAQUE , 2:DEPÓSITO , 3:PIX , 4:BOLETO
	private BigDecimal valorTransacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataTransacao;
	
	private String descricaoTransacao;
	private int idContaDestino;
	
	public ExtratoDTO(int idConta, int numeroConta, int numAgencia, int idTransacao, String tipoTransacao,
			BigDecimal valorTransacao, Date dataTransacao, String descricaoTransacao, int idContaDestino) {
		super();
		this.setIdConta(idConta); 
		this.setNumeroConta(numeroConta);
		this.setNumAgencia(numAgencia);
		this.setIdTransacao(idTransacao);
		this.setTipoTransacao(tipoTransacao);
		this.setValorTransacao(valorTransacao);
		this.setDataTransacao(dataTransacao);
		this.setDescricaoTransacao(descricaoTransacao);
		this.setIdContaDestino(idContaDestino); 
	}
	
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
	public int getIdTransacao() {
		return idTransacao;
	}
	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
	}
	public String getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(String tipoTransacao) {
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
	public int getIdContaDestino() {
		return idContaDestino;
	}
	public void setIdContaDestino(int idContaDestino) {
		this.idContaDestino = idContaDestino;
	}
	
	
	
	
}
