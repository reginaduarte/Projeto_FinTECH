package com.avanade.projeto.fintech.trustbank.dto;

import java.math.BigDecimal;

public class PixDTO {
	
    private int idContaOrigem;
    private BigDecimal valor;
    private String chavePix;
    private String descricaoTransacao;
    
	public int getIdContaOrigem() {
		return idContaOrigem;
	}
	public void setIdContaOrigem(int idContaOrigem) {
		this.idContaOrigem = idContaOrigem;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getChavePix() {
		return chavePix;
	}
	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}
	public String getDescricaoTransacao() {
		return descricaoTransacao;
	}
	public void setDescricaoTransacao(String descricaoTransacao) {
		this.descricaoTransacao = descricaoTransacao;
	}

}
