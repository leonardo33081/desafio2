package com.api.account.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.account.management.models.Pessoas;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Integer> {

}
