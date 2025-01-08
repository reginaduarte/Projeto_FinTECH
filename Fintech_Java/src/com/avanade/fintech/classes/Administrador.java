package com.avanade.fintech.classes;

public class Administrador extends Usuario {
	
	private int idAdmin;
	// nome, email e senha são herdados da classe Usuario
	public Administrador(String nomeUsuario, String emailUsuario, String senhaUsuario) {
		setNomeUsuario(nomeUsuario);
		setEmailUsuario(emailUsuario);
		setSenhaUsuario(senhaUsuario);
	}
	

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

}
