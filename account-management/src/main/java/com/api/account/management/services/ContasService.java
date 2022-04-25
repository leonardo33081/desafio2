package com.api.account.management.services;

import java.util.Optional;

import javax.persistence.Id;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.account.management.models.Contas;
import com.api.account.management.repositories.ContasRepository;

@Service
public class ContasService {

	final ContasRepository contasRepository;

	public ContasService(ContasRepository contasRepository) {
		this.contasRepository = contasRepository;
	}

	@Transactional
	public Contas save(Contas contas) {
		return contasRepository.save(contas);
	}

	public Page<Contas> findAll(Pageable pageable) {
		return contasRepository.findAll(pageable);
	}

	public Optional<Contas> findById(Integer id) {
		return contasRepository.findById(id);
	}

	@Transactional
	public void delete(Contas conta) {
		contasRepository.delete(conta);
	}

}
