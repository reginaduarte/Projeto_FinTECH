package com.avanade.projeto.fintech.trustbank.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.projeto.fintech.trustbank.dto.ExtratoDTO;
import com.avanade.projeto.fintech.trustbank.dto.TransacaoDTO;
import com.avanade.projeto.fintech.trustbank.dto.TransferenciaDTO;
import com.avanade.projeto.fintech.trustbank.dto.UsuarioContaDTO;
import com.avanade.projeto.fintech.trustbank.entities.Conta;
import com.avanade.projeto.fintech.trustbank.entities.Transacao;
import com.avanade.projeto.fintech.trustbank.services.ContaServices;
import com.avanade.projeto.fintech.trustbank.services.TransacaoServices;


@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaServices contaService;
	
	@Autowired
	private TransacaoServices transacaoService;
	
	// 1) Consulta Nativa SQL - Consultar todas as contas com CPF
	@GetMapping("/lista")
	public ResponseEntity<List<UsuarioContaDTO>> listarContas(){
		return new ResponseEntity<List<UsuarioContaDTO>>(
				contaService.listarContas(), 
				HttpStatus.OK);
	}
	
		// 2) Consulta Nativa SQL - Consultar todas as contas POR CPF
		@GetMapping("/{cpf}")
		public ResponseEntity<List<UsuarioContaDTO>> listarContasByCpf(@PathVariable("cpf") String cpf){
			return new ResponseEntity<List<UsuarioContaDTO>>(
					contaService.listarContasByCpf(cpf), 
					HttpStatus.OK);
		}
	
		
		// 3) Consulta Nativa SQL - Extrato, com critério de ID_CONTA
		
		@GetMapping("/extrato/{numConta}")
		public ResponseEntity<List<ExtratoDTO>> gerarExtrato(@PathVariable("numConta") int numConta){
			return new ResponseEntity<List<ExtratoDTO>>(
					contaService.gerarExtrato(numConta), 
					HttpStatus.OK);
		}
		
		

		// 4) Consulta Nativa SQL - INSERT DE CONTAS
	
		@PostMapping("/nova")
		public ResponseEntity<?> incluir(@RequestBody Conta conta){
		try {
			return new ResponseEntity<Conta>
			(contaService.incluirContas(conta), HttpStatus.CREATED);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
		
		// 5) Consulta Nativa SQL - Consulta de saldo de conta por CPF
		
		@GetMapping("/saldo/{cpf}")
		public ResponseEntity<List<Conta>> consultarSaldoPorCPF(@PathVariable("cpf") String cpf){
			return new ResponseEntity<List<Conta>>(
					contaService.consultarSaldoPorCPF(cpf), HttpStatus.OK);
		}
		
		
		// 6) Verificar se há saldo suficiente na Conta para efetuar uma transação
		
		@GetMapping("/verificarSaldo")
	    public String verificarSaldo(@RequestParam int idConta, @RequestParam BigDecimal valor) {
	        return contaService.verificarSaldoSuficiente(idConta, valor);
	    }
		
		// 6) Realização da transação
		
//	    @PostMapping("/realizarTransacao")
//	    public Transacao realizarTransacaoDebitar(@RequestParam int idConta, 
//	    		@RequestParam BigDecimal valor, 
//	    		@RequestParam int tipoTransacao, 
//	    		@RequestParam String descricaoTransacao ) {
//	        return transacaoService.realizarTransacaoDebitar(idConta, valor, tipoTransacao, descricaoTransacao);
//	    }
		
		// Adicionado para a transação do tipo saque 
		@PostMapping("/realizarTransacao")
		public Transacao realizarTransacaoDebitar(@RequestBody TransacaoDTO transacaoDTO) {
		    return transacaoService.realizarTransacaoDebitar(
		            transacaoDTO.getIdConta(), 
		            transacaoDTO.getValor(), 
		            transacaoDTO.getTipoTransacao(), 
		            transacaoDTO.getDescricaoTransacao(),
		    		transacaoDTO.getTemTarifa());
		}
		
		// Adicionado para a transação do tipo depósito
		@PostMapping("/depositar")
		public Transacao realizarTransacaoCreditar(@RequestBody TransacaoDTO transacaoDTO) {
		    return transacaoService.realizarTransacaoCreditar(
		            transacaoDTO.getIdConta(), 
		            transacaoDTO.getValor(), 
		            transacaoDTO.getTipoTransacao(), 
		            transacaoDTO.getDescricaoTransacao());
		}

	
	    // 7) Transferência para outra conta existente no sistema
		@PostMapping("/transferir")
		public Transacao transferirParaOutraConta(@RequestBody TransferenciaDTO transferenciaDTO) {
		    return transacaoService.transferirParaOutraConta(
		        transferenciaDTO.getIdConta(),
		        transferenciaDTO.getNumeroAgenciaDestino(),
		        transferenciaDTO.getNumeroContaDestino(),
		        transferenciaDTO.getValor(),
		        transferenciaDTO.getDescricaoTransacao(),
		        transferenciaDTO.getTemTarifa()
		    );
		}
		
		// Adicionado o método para incluir conta por CPF
		@PostMapping("/nova/{cpf}")
		public ResponseEntity<?> incluirContaPorCpf(@PathVariable("cpf") String cpf, @RequestBody Conta conta) {
		    try {
		        return new ResponseEntity<>(contaService.incluirContaPorCpf(cpf, conta), HttpStatus.CREATED);
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		    }
		}
	    
}

