package com.avanade.fintech.classes;

import java.util.Date;

public class Tarifa {
	
	private int idTarifa;
	private double valorTarifa;
	private Date dataTarifa;
	//idTransacao vem da classe Transacao
	
	
	public int getIdTarifa() {
		return idTarifa;
	}
	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}
	public double getValorTarifa() {
		return valorTarifa;
	}
	public void setValorTarifa(double valorTarifa) {
		this.valorTarifa = valorTarifa;
	}
	public Date getDataTarifa() {
		return dataTarifa;
	}
	public void setDataTarifa(Date dataTarifa) {
		this.dataTarifa = dataTarifa;
	}

}
