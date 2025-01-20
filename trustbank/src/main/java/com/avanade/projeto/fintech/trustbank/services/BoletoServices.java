package com.avanade.projeto.fintech.trustbank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Boleto;
import com.avanade.projeto.fintech.trustbank.repository.BoletoRepository;

@Service
public class BoletoServices {


	@Autowired
	private BoletoRepository boletoRepository;
	
	public List<Boleto> listarBoletos(){
		return boletoRepository.findAll(); 
	}
	
	public Boleto incluirBoleto(Boleto boleto) {
		return boletoRepository.save(boleto);
	}
	
	
}
