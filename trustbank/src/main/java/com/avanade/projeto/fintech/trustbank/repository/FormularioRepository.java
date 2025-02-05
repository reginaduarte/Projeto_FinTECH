package com.avanade.projeto.fintech.trustbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avanade.projeto.fintech.trustbank.entities.Formulario;

public interface FormularioRepository extends JpaRepository<Formulario, Integer> {

	
	@Query(value = "SELECT * FROM FORMULARIO WHERE STATUS_SOLICITACAO = 1", nativeQuery = true)
	List<Formulario> listarSolicitacoes();
	
	Formulario findByCpf(String cpf);
	
	void deleteByCpf(String cpf); // Adicionando a exclusão pelo CPF
	
}
