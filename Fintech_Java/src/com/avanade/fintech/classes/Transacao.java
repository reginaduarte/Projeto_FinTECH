package com.avanade.fintech.classes;

import java.util.Date;

public class Transacao {
	
	private int idTransacao;
	private int tipoTransacao;
	private double valorTransacao;
	private Date dataTransacao;
	private String descricaoTransacao;
	//idConta vem da classe Conta
	
	
	
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
	public double getValorTransacao() {
		return valorTransacao;
	}
	public void setValorTransacao(double valorTransacao) {
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
	

}
