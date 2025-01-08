package com.avanade.fintech.classes;

import java.util.Date;

public class Pix {
	
	private int idPix;
	private String chavePix;
	private int tipoChave;
	private Date dataCriacao;
	//idTransacao vem da classe Transacao
	
	
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
