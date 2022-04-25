package com.api.account.management.dtos;

public class ExtratoDTO {

	private Integer idConta;
	private Integer idTransacao;
	private String dataTransacao;
	private String tipoTransacao;
	private Double valorTransacao;
	public Integer getIdConta() {
		return idConta;
	}
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	public Integer getIdTransacao() {
		return idTransacao;
	}
	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}
	public String getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(String dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public Double getValorTransacao() {
		return valorTransacao;
	}
	public void setValorTransacao(Double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}
	
	public String getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	@Override
	public String toString() {
		return "ExtratoDTO [idConta=" + idConta + ", idTransacao=" + idTransacao + ", dataTransacao=" + dataTransacao
				+ ", tipoTransacao=" + tipoTransacao + ", valorTransacao=" + valorTransacao + "]";
	}
	
	
	
}
