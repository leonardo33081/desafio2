package com.api.account.management.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.account.management.models.Pessoas;
import com.api.account.management.repositories.PessoasRepository;

@Service
public class PessoasService {

	final PessoasRepository pessoaRepository;
	
	public PessoasService(PessoasRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	@Transactional
	public Pessoas save(Pessoas pessoas) {
		return pessoaRepository.save(pessoas);
	}

	public Page<Pessoas> findAll(Pageable pageable) {
		return pessoaRepository.findAll(pageable);
	}

	public Optional<Pessoas> findById(Integer id) {
		return pessoaRepository.findById(id);
	}

	@Transactional
	public void delete(Pessoas pessoa) {
		pessoaRepository.delete(pessoa);
	}

}
