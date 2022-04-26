package com.api.account.management.dtos;

public class Resposta {
	public Resposta(String mensagem){
		this.mensagem = mensagem;
	}
	
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}
	
}
