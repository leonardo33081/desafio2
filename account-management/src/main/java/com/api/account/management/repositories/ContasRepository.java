package com.api.account.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.account.management.models.Contas;

@Repository
public interface ContasRepository extends JpaRepository<Contas, Integer> {

}
