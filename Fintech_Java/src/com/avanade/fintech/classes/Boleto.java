package com.avanade.fintech.classes;

import java.util.Date;

public class Boleto {
	
	private int idBoleto;
	private String codigoBoleto;
	private Date dataVencimento;
	//idTransacao vem da classe Transacao
	
	
	public int getIdBoleto() {
		return idBoleto;
	}
	public void setIdBoleto(int idBoleto) {
		this.idBoleto = idBoleto;
	}
	public String getCodigoBoleto() {
		return codigoBoleto;
	}
	public void setCodigoBoleto(String codigoBoleto) {
		this.codigoBoleto = codigoBoleto;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

}
