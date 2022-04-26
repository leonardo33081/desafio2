package com.api.account.management.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.account.management.dtos.TransacaoDTO;
import com.api.account.management.models.Contas;
import com.api.account.management.models.Transacoes;
import com.api.account.management.services.ContasService;
import com.api.account.management.services.TransacoesService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

	private static final String SAQUE = "SAQUE";
	private static final String DEPOSITO = "DEPOSITO";

	@Autowired
	TransacoesService transacoesService;

	@Autowired
	ContasService contaService;

	/**
	 * escopo mínimo: Implementar path que realiza operação de depósito em uma conta.
	 * @author Leonardo.Coutinho
	 * @param TransacaoDTO
	 * @return ResponseEntity<Object>
	 **/
	@PutMapping("/deposito")
	public ResponseEntity<Object> depositoEmConta(@RequestBody @Valid TransacaoDTO transacaoDto) {
		try {
			Optional<Contas> conta = contaService.findById(transacaoDto.getIdConta());
			if (!conta.isPresent()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deposito nao pode ser realizado para a conta "
						+ transacaoDto.getIdConta() + ". Conta nao existe!");
			}

			Transacoes transacao = converter(transacaoDto);
			transacao.setDataTransacao(obterDataAtual());
			transacao.setTipo(DEPOSITO);
			Transacoes transacaoCriada = transacoesService.save(transacao);
			

			Contas contaEmAtualizacao = conta.get();
			Double novoSaldo = obterSaldoFormatado(contaEmAtualizacao.getSaldo() + transacaoCriada.getValor());
			contaEmAtualizacao.setSaldo(novoSaldo);

			// verificar se cria uma nova ou se atualiza
			contaService.save(contaEmAtualizacao);

			return ResponseEntity.status(HttpStatus.CREATED).body(
					"Deposito realizado para a conta " + transacaoDto.getIdConta() + ". Novo saldo::" + novoSaldo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erro ao depositar na conta." + transacaoDto.getIdConta() + " Motivo:: " + e.getMessage());
		}
	}

	private Date obterDataAtual() {
		return new Date();
	}

	/**
	 * escopo mínimo: Implementar path que realiza operação de saque em uma conta.
	 * 
	 * @author Leonardo.Coutinho
	 * @param TransacaoDTO
	 * @return ResponseEntity<Object>
	 **/
	@PutMapping("/saque")
	public ResponseEntity<Object> saqueEmConta(@RequestBody @Valid TransacaoDTO transacaoDto) {
		try {
			
			Optional<Contas> conta = contaService.findById(transacaoDto.getIdConta());
			if (!conta.isPresent()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Saque nao pode ser realizado para a conta "
						+ transacaoDto.getIdConta() + ". Conta nao existe!");
			}

			Transacoes transacao = converter(transacaoDto);
			transacao.setDataTransacao(obterDataAtual());
			transacao.setTipo(SAQUE);
			Transacoes transacaoCriada = transacoesService.save(transacao);

			Contas contaEmAtualizacao = conta.get();
			Double novoSaldo = obterSaldoFormatado( contaEmAtualizacao.getSaldo() - transacaoCriada.getValor()); 
			
			if(!validarPossibilidadeDeSaque(novoSaldo)) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
						"Saque nao pode ser realizado para a conta " + transacaoDto.getIdConta() + ". Saldo insuficiente!");
			
			}
			
			contaEmAtualizacao.setSaldo(novoSaldo);

			// verificar se cria uma nova ou se atualiza
			contaService.save(contaEmAtualizacao);

			return ResponseEntity.status(HttpStatus.CREATED).body(
					"Saque realizado para a conta " + transacaoDto.getIdConta() + ". Novo saldo::" + novoSaldo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erro ao sacar da conta." + transacaoDto.getIdConta() + " Motivo:: " + e.getMessage());
		}
	}

	private Double obterSaldoFormatado(double d) {
		return new BigDecimal( d ).setScale( 2, BigDecimal.ROUND_HALF_EVEN ).doubleValue();
	}

	private boolean validarPossibilidadeDeSaque(Double novoSaldo) {
		return novoSaldo >= 0;
	}

	private Transacoes converter(@Valid TransacaoDTO contaDto) {
		Transacoes transacao = new Transacoes();
		
		Contas conta = new Contas();
		conta.setIdConta(contaDto.getIdConta());
		transacao.setConta(conta);
		transacao.setValor(contaDto.getValor());
		
		return transacao;
	}
}
