package com.avanade.projeto.fintech.trustbank.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "BOLETO")
public class Boleto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_BOLETO")
	private int idBoleto;
	
	@Column(name = "CODIGO_BOLETO")
	private String codigoBoleto;
	
	@Column(name = "DATA_VENCIMENTO")
	@Temporal(value = TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataVencimento;
	
	//idTransacao vem da classe Transacao
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TRANSACAO")
	private Transacao transacao;
	
	
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
