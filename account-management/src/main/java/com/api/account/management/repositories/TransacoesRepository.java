package com.api.account.management.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.account.management.models.Contas;
import com.api.account.management.models.Transacoes;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Integer> {
	
	List<Transacoes> findByConta(Contas conta);
	
	@Query("select t from Transacoes t where conta.idConta = ?1 and t.dataTransacao >= ?2")
	List<Transacoes> findByContaPeriodo(Integer idConta, Date dataInicio);

}
