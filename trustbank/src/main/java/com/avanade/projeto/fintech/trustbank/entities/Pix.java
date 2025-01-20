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
@Table(name = "PIX")
public class Pix {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PIX")
	private int idPix;
	
	@Column(name = "CHAVE_PIX")
	private String chavePix;
	
	@Column(name = "TIPO_CHAVE") // -- 1: CPF, 2: CNPJ, 3: EMAIL, 4: TELEFONE, 5: CHAVE ALEATÓRIA
	private int tipoChave;
	
	@Column(name = "DATA_CRIACAO")
	@Temporal(value = TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataCriacao;
	//idTransacao vem da classe Transacao
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TRANSACAO")
	private Transacao transacao;
	
	
	
	public int getIdPix() {
		return idPix;
	}
	public void setIdPix(int idPix) {
		this.idPix = idPix;
	}
	public String getChavePix() {
		return chavePix;
	}
	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}
	public int getTipoChave() {
		return tipoChave;
	}
	public void setTipoChave(int tipoChave) {
		this.tipoChave = tipoChave;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
