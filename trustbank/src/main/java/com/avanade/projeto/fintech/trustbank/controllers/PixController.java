package com.avanade.projeto.fintech.trustbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.projeto.fintech.trustbank.dto.PixDTO;
import com.avanade.projeto.fintech.trustbank.entities.Pix;
import com.avanade.projeto.fintech.trustbank.entities.Transacao;
import com.avanade.projeto.fintech.trustbank.services.PixServices;

@RestController
@RequestMapping("/pix")

public class PixController {

	@Autowired
	private PixServices pixService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Pix>> listarPixes(){
		
		return new ResponseEntity<List<Pix>>(
				pixService.listarPixes(), 
				HttpStatus.OK);
	}
	
	@PostMapping("/novo")
	public ResponseEntity<?> incluir(@RequestBody Pix pix){
		try {
			
			return new ResponseEntity<Pix>
			(pixService.incluirPix(pix), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
	
//	@PostMapping("/processar")
//    public ResponseEntity<?> processarPix(@RequestParam int idContaOrigem,
//    									  @RequestParam BigDecimal valor,
//    									  @RequestParam String chavePix, 
//    									  @RequestParam String descricaoTransacao) {
//        Transacao transacaoPix = pixService.processarPix(idContaOrigem, valor, chavePix, descricaoTransacao);
//        
//        if (transacaoPix != null) {
//            return new ResponseEntity<Transacao>(transacaoPix, HttpStatus.CREATED);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PIX não encontrado ou inválido");
//        }
//    }
	
    @PostMapping("/processar")
    public ResponseEntity<?> processarPix(@RequestBody PixDTO pixRequestDTO) {
        // Processa a transação Pix com base nos dados recebidos
        Transacao transacaoPix = pixService.processarPix(pixRequestDTO.getIdContaOrigem(), 
                                                        pixRequestDTO.getValor(), 
                                                        pixRequestDTO.getChavePix(), 
                                                        pixRequestDTO.getDescricaoTransacao());

        // Retorna o resultado da transação
        if (transacaoPix != null) {
            return new ResponseEntity<>(transacaoPix, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PIX não encontrado ou inválido");
        }
    }
	

}