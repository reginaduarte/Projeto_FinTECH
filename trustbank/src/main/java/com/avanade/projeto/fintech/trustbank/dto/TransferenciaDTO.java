package com.avanade.projeto.fintech.trustbank.dto;

import java.math.BigDecimal;

public class TransferenciaDTO {
    private int idConta;
    private int numeroAgenciaDestino;
    private int numeroContaDestino;
    private BigDecimal valor;
    private String descricaoTransacao;
    private int temTarifa;
	public int getIdConta() {
		return idConta;
	}
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	public int getNumeroAgenciaDestino() {
		return numeroAgenciaDestino;
	}
	public void setNumeroAgenciaDestino(int numeroAgenciaDestino) {
		this.numeroAgenciaDestino = numeroAgenciaDestino;
	}
	public int getNumeroContaDestino() {
		return numeroContaDestino;
	}
	public void setNumeroContaDestino(int numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getDescricaoTransacao() {
		return descricaoTransacao;
	}
	public void setDescricaoTransacao(String descricaoTransacao) {
		this.descricaoTransacao = descricaoTransacao;
	}
	public int getTemTarifa() {
		return temTarifa;
	}
	public void setTemTarifa(int temTarifa) {
		this.temTarifa = temTarifa;
	}
}