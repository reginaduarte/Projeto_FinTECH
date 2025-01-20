package com.avanade.projeto.fintech.trustbank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Tarifa;
import com.avanade.projeto.fintech.trustbank.repository.TarifaRepository;


@Service
public class TarifaServices {

	@Autowired
	private TarifaRepository tarifaRepository;
	
	public List<Tarifa> listarTarifas(){
		return tarifaRepository.findAll(); 
	}
	
	public Tarifa incluirTarifa(Tarifa tarifa) {
		return tarifaRepository.save(tarifa);
	}
	
}
