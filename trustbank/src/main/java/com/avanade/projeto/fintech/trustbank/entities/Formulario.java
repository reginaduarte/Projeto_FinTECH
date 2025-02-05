package com.avanade.projeto.fintech.trustbank.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
 
@Entity
@Table(name = "FORMULARIO")
public class Formulario {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ABERTURA", nullable = false)
    private Long idSolicitacao;
 
    @Column(name = "NOME", nullable = false)
    private String nome;
 
    @Column(name = "CPF", nullable = false, unique = true)
    private String cpf;
 
    @Column(name = "EMAIL")
    private String email;
    
	@Column(name = "TELEFONE")
    private String telefone;
	
	@Column(name = "CEP")
	private String cep;
	
	@Column(name = "LOGRADOURO")
	private String logradouro;
	
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Column(name = "NUMERO")
	private int numero;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "ESTADO")
	private String estado;
	
	@Column(name = "SENHA")
	private String senha;
 
    @Column(name = "STATUS_SOLICITACAO", nullable = false)  
    private int status = 1; // status: 1 - pendente, 2 - autorizado, 3 - não autorizado
 
    @Column(name = "DATA_SOLICITACAO")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataSolicitacao;
 
    public Long getIdSolicitacao() {
		return idSolicitacao;
	}
 
	public void setIdSolicitacao(Long idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
 
	public String getNome() {
		return nome;
	}
 
	public void setNome(String nome) {
		this.nome = nome;
	}
 
	public String getCpf() {
		return cpf;
	}
 
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public String getTelefone() {
		return telefone;
	}
 
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
 
	public int getStatus() {
		return status;
	}
 
	public void setStatus(int status) {
		this.status = status;
	}
 
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}
 
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
 
	
	
}
 
