package com.avanade.projeto.fintech.trustbank.dto;

import java.math.BigDecimal;

public class TransacaoDTO {
    private int idConta;
    private BigDecimal valor;
    private int tipoTransacao;
    private String descricaoTransacao;
    private int temTarifa;

    // Getters e Setters
    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(int tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
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
