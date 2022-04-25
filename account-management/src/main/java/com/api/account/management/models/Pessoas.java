package com.api.account.management.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PESSOAS", schema = "LEO")
public class Pessoas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="idpessoa")
	private Integer idPessoa;
	 
	@Column(nullable = false, name="nome")
	private String nome;
	
	@Column(nullable = false, name="cpf")
	private String cpf;
	
	@Column(nullable = false, name="datanascimento")
	private Date dataNascimento;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa")
	private Set<Contas> contas;
	
	public Set<Contas> getContas() {
		return contas;
	}

	public void setContas(Set<Contas> contas) {
		this.contas = contas;
	}
	
	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
}
