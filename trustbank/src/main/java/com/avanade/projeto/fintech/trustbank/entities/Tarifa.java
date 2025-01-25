package com.avanade.projeto.fintech.trustbank.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "TARIFA")
public class Tarifa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TARIFA")
	private int idTarifa;
	
	@Column(name = "VALOR_TARIFA")
	private double valorTarifa;
	
	@Column(name = "DATA_TARIFA")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataTarifa;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TRANSACAO")
	private Transacao transacao;
	
	
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
