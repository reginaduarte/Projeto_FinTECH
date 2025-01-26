package com.avanade.projeto.fintech.trustbank.dto;

public class LoginResponseDTO {
	
	private String mensagem;
    private int idConta;

    // Construtores
    public LoginResponseDTO(String mensagem, int idConta) {
        this.mensagem = mensagem;
        this.idConta = idConta;
    }

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
    
}
