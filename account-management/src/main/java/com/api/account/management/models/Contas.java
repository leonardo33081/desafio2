package com.api.account.management.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CONTAS", schema = "LEO")
public class Contas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idconta")
	private Integer idConta;
	
	@ManyToOne
	@JoinColumn(name = "idpessoa", referencedColumnName = "idpessoa")
	private Pessoas pessoa;
	
	@OneToMany(mappedBy="conta")
	private Set<Transacoes> transacoes;
	
	@Column(nullable = false, name = "saldo")
	private Double saldo;
	
	@Column(nullable = false, name = "limitesaquediario")
	private Double limiteSaqueDiario;
	
	@Column(nullable = false, name = "flagativo")
	private Boolean flagAtivo;
	
	@Column(nullable = false, name = "tipoconta")
	private Integer tipoConta;
	
	@Column(nullable = false, name = "datacriacao")
	private Date dataCriacao;

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
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

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
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
	
}
