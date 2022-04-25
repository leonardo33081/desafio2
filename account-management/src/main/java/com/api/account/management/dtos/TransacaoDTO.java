package com.api.account.management.dtos;

public class TransacaoDTO {

	@javax.validation.constraints.NotNull
	private Integer idConta;
	@javax.validation.constraints.NotNull
	private Double valor;
	public Integer getIdConta() {
		return idConta;
	}
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "TransacaoDTO [idConta=" + idConta + ", valor=" + valor + "]";
	}
	
	
}
