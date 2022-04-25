package com.api.account.management.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.account.management.dtos.PessoaDTO;
import com.api.account.management.models.Pessoas;
import com.api.account.management.services.PessoasService;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {

	@Autowired
	PessoasService pessoaService;

	/**
	 * @author Leonardo.Coutinho
	 * @param ContaDTO
	 * @return ResponseEntity<Object>
	 **/
	@PostMapping
	public ResponseEntity<Object> saveConta(@RequestBody @Valid PessoaDTO pessoaDto) {
		try {
			Pessoas pessoa = pessoaService.save(converter(pessoaDto));

			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Pessoa " + pessoa.getIdPessoa() + " adicionada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erro ao salvar pessoa. Motivo:: " + e.getMessage());
		}
	}

	private Pessoas converter(@Valid PessoaDTO pessoaDto) {
		Pessoas pessoa = new Pessoas();
		BeanUtils.copyProperties(pessoaDto, pessoa);
		return pessoa;
	}

}
