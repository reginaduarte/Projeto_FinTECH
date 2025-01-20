package com.avanade.projeto.fintech.trustbank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Pix;
import com.avanade.projeto.fintech.trustbank.repository.PixRepository;


@Service
public class PixServices {

	@Autowired
	private PixRepository pixRepository;
	
	public List<Pix> listarPixes(){
		return pixRepository.findAll(); 
	}
	
	public Pix incluirPix(Pix pix) {
		return pixRepository.save(pix);
	}
	
	
	
}
