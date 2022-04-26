package com.api.account.management.dtos;

public class ParametroExtratoPeriodoDTO {
	@javax.validation.constraints.NotNull
	private Integer idConta;
	@javax.validation.constraints.NotNull
	private String dataInicial;
	
	public Integer getIdConta() {
		return idConta;
	}
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	
}
