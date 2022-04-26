package com.api.account.management.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.account.management.dtos.ContaDTO;
import com.api.account.management.dtos.ExtratoDTO;
import com.api.account.management.dtos.Resposta;
import com.api.account.management.models.Contas;
import com.api.account.management.models.Pessoas;
import com.api.account.management.models.Transacoes;
import com.api.account.management.services.ContasService;
import com.api.account.management.services.PessoasService;
import com.api.account.management.services.TransacoesService;

@RestController
@RequestMapping("/contas")
public class ContasController {

	@Autowired
	PessoasService pessoaService;
	@Autowired
	TransacoesService transacoesService;
	
	final ContasService contasService;

	public ContasController(ContasService contasService) {
		this.contasService = contasService;
	}

	/**
	 * escopo mínimo: Implementar path que realiza a criação de uma conta;
	 * 
	 * @author Leonardo.Coutinho
	 * @param ContaDTO
	 * @return ResponseEntity<Object>
	 **/
	@PostMapping("/nova")
	public ResponseEntity<Object> saveConta(@RequestBody @Valid ContaDTO contaDto) {
		try {
			Optional<Pessoas> pessoaConta = obterPessoaConta(contaDto.getIdPessoa());

			if (!pessoaConta.isPresent()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não foi possível incluir a conta. "
						+ "Pessoa não encontrada para o identificador " + contaDto.getIdPessoa());
			}

			Contas conta = converter(contaDto);

			conta.setPessoa(pessoaConta.get());

			Contas contaSalva = contasService.save(conta);
			 

			return ResponseEntity.status(HttpStatus.CREATED).body(new Resposta("Conta " + contaSalva.getIdConta() + " criada com sucesso!"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erro ao salvar conta. Motivo:: " + e.getMessage());
		}

	}

	/**
	 * escopo mínimo: Implementar path que realiza operação de consulta de saldo em determinada conta;
	 * 
	 * @author Leonardo.Coutinho
	 * @param ContaDTO
	 * @return ResponseEntity<Object>
	 **/
	@GetMapping("/saldo")
	public ResponseEntity<Object> consultarSaldoConta(@PathVariable(value = "idConta") Integer idConta) {
		try {
			Optional<Contas> conta = contasService.findById(idConta);
			
			if (!conta.isPresent()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não foi possível obter o saldo da conta para identificador " + idConta );
			}
			
			return ResponseEntity.status(HttpStatus.CREATED) .body(conta.get().getSaldo());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao obter saldo da conta. Motivo:: " + e.getMessage());
		}

	}
	
	
	
	
	/**
	 * escopo mínimo: Implementar path que realiza o bloqueio de uma conta;
	 * 
	 * @author Leonardo.Coutinho
	 * @param ContaDTO
	 * @return ResponseEntity<Object>
	 **/
	@PutMapping("/bloqueio/{idConta}")
	public ResponseEntity<Object> bloquearConta(@PathVariable(value = "idConta") Integer idConta) {
		try {
			Optional<Contas> conta = contasService.findById(idConta);

			if (!conta.isPresent()) {
				return respostaPadraoContaNaoEncontrada(idConta);
			} 
			Contas contaRecuperada = conta.get();
			contaRecuperada.setFlagAtivo(Boolean.FALSE);
			Contas contaSalva = contasService.save(contaRecuperada);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Conta " + contaSalva.getIdConta() + " bloqueada com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erro ao bloquear conta. Motivo:: " + e.getMessage());
		}

	}

	private ResponseEntity<Object> respostaPadraoContaNaoEncontrada(Integer idConta) {
		return ResponseEntity.status(HttpStatus.CREATED).body("Não foi possível recuperar a conta para o identificador " + idConta);
	}

	/**
	 * realiza o desbloqueio de uma conta;
	 * 
	 * @author Leonardo.Coutinho
	 * @param ContaDTO
	 * @return ResponseEntity<Object>
	 **/
	@PutMapping("/desbloqueio/{idConta}")
	public ResponseEntity<Object> desbloquearConta(@PathVariable(value = "idConta")  Integer idConta) {
		try {
			Optional<Contas> conta = contasService.findById(idConta);
			if (!conta.isPresent()) {
				return respostaPadraoContaNaoEncontrada(idConta);
			} 
			Contas contaRecuperada = conta.get();
			contaRecuperada.setFlagAtivo(Boolean.TRUE);

			Contas contaSalva = contasService.save(contaRecuperada);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Conta " + contaSalva.getIdConta() + " desbloqueada com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erro ao desbloquear conta. Motivo:: " + e.getMessage());
		}

	}

	private Optional<Pessoas> obterPessoaConta(Integer idPessoa) {
		Pessoas p = new Pessoas();
		p.setIdPessoa(idPessoa);

		return pessoaService.findById(idPessoa);
	}

	private Contas converter(@Valid ContaDTO contaDto) {
		Contas conta = new Contas();
		BeanUtils.copyProperties(contaDto, conta);
		return conta;
	}

	
	/**
	 * escopo mínimo: Implementar path que recupera o extrato de transações de uma conta;
	 * 
	 * @author Leonardo.Coutinho
	 * @param ContaDTO
	 * @return ResponseEntity<Object>
	 **/
	@GetMapping("/extrato/{idConta}")
	public ResponseEntity<Object> extratoConta(@PathVariable(value = "idConta") Integer idConta) {
		try {
			Contas conta = new Contas();
			conta.setIdConta(idConta);
			
			List<Transacoes> lista = transacoesService.findByConta(conta);

			List<ExtratoDTO> listaExtrato = gerarExtrato(lista, idConta);
			Map<String, List<ExtratoDTO>> mapaRetorno = new HashMap<String, List<ExtratoDTO>>();
			mapaRetorno.put("Extrato:", listaExtrato);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(mapaRetorno);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erro emitir extrato da conta. Motivo:: " + e.getMessage());
		}

	}

	private List<ExtratoDTO> gerarExtrato(List<Transacoes> lista, Integer idConta) {
		List<ExtratoDTO> retorno = new ArrayList<ExtratoDTO>();
		for (Transacoes transacao : lista) {
			ExtratoDTO extrato = new ExtratoDTO();
			
			extrato.setDataTransacao(formatarData(transacao.getDataTransacao()));
			extrato.setIdConta(idConta);
			extrato.setIdTransacao(transacao.getIdTransacao());
			extrato.setValorTransacao(transacao.getValor());
			extrato.setTipoTransacao(transacao.getTipo());
			
			retorno.add(extrato);
		}
		return retorno;
	}

	private String formatarData(Date dataTransacao) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		return simpleDateFormat.format(dataTransacao);
		
	}
}
