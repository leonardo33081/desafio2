package com.api.account.management.dtos;

import java.util.Date;

public class ContaDTO {
	
	@javax.validation.constraints.NotNull
	private Integer idPessoa;
	@javax.validation.constraints.NotNull
	private Double saldo;
	@javax.validation.constraints.NotNull
	private Double limiteSaqueDiario;
	@javax.validation.constraints.NotNull
	private Boolean flagAtivo;
	@javax.validation.constraints.NotNull
	private Integer tipoConta;
	@javax.validation.constraints.NotNull
	private Date dataCriacao;
	
	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}

	public void setLimiteSaqueDiario(Double limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public Integer getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public String toString() {
		return "ContaDTO [idPessoa=" + idPessoa + ", saldo=" + saldo + ", limiteSaqueDiario=" + limiteSaqueDiario
				+ ", flagAtivo=" + flagAtivo + ", tipoConta=" + tipoConta + ", dataCriacao=" + dataCriacao + "]";
	}



}
