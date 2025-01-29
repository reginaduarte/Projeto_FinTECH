package com.avanade.projeto.fintech.trustbank.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.projeto.fintech.trustbank.dto.BoletoDTO;
import com.avanade.projeto.fintech.trustbank.entities.Boleto;
import com.avanade.projeto.fintech.trustbank.entities.Transacao;
import com.avanade.projeto.fintech.trustbank.services.BoletoServices;

@RestController
@RequestMapping("/boleto")

public class BoletoController {

	@Autowired
	private BoletoServices boletoServices;
	
	
	
	@GetMapping("/lista")
	public ResponseEntity<List<Boleto>> listarBoletos(){
		
		return new ResponseEntity<List<Boleto>>(
				boletoServices.listarBoletos(), 
				HttpStatus.OK);
	}
	
	@PostMapping("/novo")
	public ResponseEntity<?> incluir(@RequestBody Boleto boleto){
		try {
			
			return new ResponseEntity<Boleto>
			(boletoServices.incluirBoleto(boleto), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	
	}
	
//	@PostMapping("/processar")
//	public ResponseEntity<?> processsarBoleto(@RequestParam int idConta, 
//			@RequestParam String codigoBoleto, 
//			@RequestParam String descricaoTransacao) {
//			
//			return new ResponseEntity<Transacao>
//			(boletoService.processarBoleto(idConta, codigoBoleto, descricaoTransacao), HttpStatus.CREATED);
//	
//	}
	@PostMapping("/processar")
	public ResponseEntity<Transacao> processarBoleto(@RequestBody BoletoDTO boletoRequestDTO) {
	    // Chamando o serviço para processar o boleto
	    Transacao transacao = boletoServices.processarBoleto(
	        boletoRequestDTO.getIdConta(), 
	        boletoRequestDTO.getCodigoBoleto(), 
	        boletoRequestDTO.getDescricaoTransacao()
	    );
	    
	    return new ResponseEntity<>(transacao, HttpStatus.CREATED);
	}
	
	// Adicionado o método para consultar a data de vencimento de um boleto
		@GetMapping("/vencimento/{codigoBoleto}")
		public ResponseEntity<?> consultarVencimento(@PathVariable("codigoBoleto") String codigoBoleto) {
			try {
				// Buscar a data de vencimento
				LocalDateTime dataVencimento = boletoServices.consultarVencimentoPorCodigoBoleto(codigoBoleto);
				return ResponseEntity.ok(dataVencimento);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Boleto não encontrado!");
			}
		}
		
	    // Método listar informações de um boleto pelo código de barras
	    @GetMapping("/lista/{codigoBoleto}")
	    public Boleto getBoletoInfo(@PathVariable String codigoBoleto) {
	        // Buscando o boleto com o código de barras
	        Boleto boleto = boletoServices.buscarBoletoPorCodigo(codigoBoleto);
	        if (boleto == null) {
	            throw new RuntimeException("Boleto não encontrado para o código informado.");
	        }
	        return boleto;
	    }
	
	
}

