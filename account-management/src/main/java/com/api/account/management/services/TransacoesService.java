package com.api.account.management.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.account.management.models.Contas;
import com.api.account.management.models.Transacoes;
import com.api.account.management.repositories.TransacoesRepository;

@Service
public class TransacoesService {

	final TransacoesRepository transacoesRepository;

	public TransacoesService(TransacoesRepository transacoesRepository) {
		this.transacoesRepository = transacoesRepository;
	}

	@Transactional
	public Transacoes save(Transacoes transacao) {
		return transacoesRepository.save(transacao);
	}

	public Page<Transacoes> findAll(Pageable pageable) {
		return transacoesRepository.findAll(pageable);
	}

	public Optional<Transacoes> findById(Integer id) {
		return transacoesRepository.findById(id);
	}

	public List<Transacoes> findByConta(Contas conta) {
		return transacoesRepository.findByConta(conta);
	}

	public List<Transacoes> findByContaPeriodo(Integer idConta, Date dataInicio){
		return transacoesRepository.findByContaPeriodo(idConta, dataInicio);
	}
	
	@Transactional
	public void delete(Transacoes transacao) {
		transacoesRepository.delete(transacao);
	}

}
