package com.avanade.projeto.fintech.trustbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanade.projeto.fintech.trustbank.entities.Tarifa;

public interface TarifaRepository extends JpaRepository <Tarifa, Integer> {

	// 1) Encontrar tarifa pelo Id_Tarifa
	
	Tarifa findByIdTarifa(int idTarifa);
}
