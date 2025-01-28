package com.avanade.projeto.fintech.trustbank.dto;

public class BoletoDTO {
    private int idConta;
    private String codigoBoleto;
    private String descricaoTransacao;
	public int getIdConta() {
		return idConta;
	}
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	public String getCodigoBoleto() {
		return codigoBoleto;
	}
	public void setCodigoBoleto(String codigoBoleto) {
		this.codigoBoleto = codigoBoleto;
	}
	public String getDescricaoTransacao() {
		return descricaoTransacao;
	}
	public void setDescricaoTransacao(String descricaoTransacao) {
		this.descricaoTransacao = descricaoTransacao;
	}

    
}

