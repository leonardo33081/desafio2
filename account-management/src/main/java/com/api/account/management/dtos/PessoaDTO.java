package com.api.account.management.dtos;

import java.util.Date;

public class PessoaDTO {
	@javax.validation.constraints.NotNull
	private String nome;
	@javax.validation.constraints.NotNull
	private String cpf;
	@javax.validation.constraints.NotNull
	private Date dataNascimento;
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
	@Override
	public String toString() {
		return "PessoaDTO [nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + "]";
	}
	
	
}
