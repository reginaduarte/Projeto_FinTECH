package com.avanade.projeto.fintech.trustbank.services;

import java.math.BigDecimal;
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
	
public BigDecimal calcularTarifa(int temTarifa, BigDecimal valor) {
		
		BigDecimal valor1 = new BigDecimal("4.00");
		BigDecimal valor2 = new BigDecimal("3.00");

		
		if(temTarifa == 1) {	// Tarifa 1
			valor = valor.add(valor1);
		}
		
		if(temTarifa == 2) {
			valor = valor.add(valor2);
		}
		
		return valor;
		
	}
	
}
