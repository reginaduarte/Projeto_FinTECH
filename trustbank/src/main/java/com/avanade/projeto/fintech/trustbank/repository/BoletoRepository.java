package com.avanade.projeto.fintech.trustbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanade.projeto.fintech.trustbank.entities.Boleto;

public interface BoletoRepository extends JpaRepository <Boleto, Integer> {

	
	
}
