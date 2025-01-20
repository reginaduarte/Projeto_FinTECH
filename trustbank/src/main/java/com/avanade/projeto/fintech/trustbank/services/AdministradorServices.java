package com.avanade.projeto.fintech.trustbank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Administrador;
import com.avanade.projeto.fintech.trustbank.repository.AdministradorRepository;


@Service
public class AdministradorServices {
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	public List<Administrador> listarAdmins(){
		return administradorRepository.findAll(); 
	}
	
	public Administrador incluirAdmin(Administrador admin) {
		return administradorRepository.save(admin);
	}
	
	
}
