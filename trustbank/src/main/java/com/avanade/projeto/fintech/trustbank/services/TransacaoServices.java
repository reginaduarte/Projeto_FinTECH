package com.avanade.projeto.fintech.trustbank.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Conta;
import com.avanade.projeto.fintech.trustbank.entities.Transacao;
import com.avanade.projeto.fintech.trustbank.repository.ContaRepository;
import com.avanade.projeto.fintech.trustbank.repository.TransacaoRepository;


@Service
public class TransacaoServices {

	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private ContaServices contaService;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TarifaServices tarifaServices;
	
	
	// 1) Listar as transações
	
	public List<Transacao> listarTransacoes(){
		return transacaoRepository.findAll(); 
	}
	
	// 2) Incluir as transações
	
	public Transacao incluirTransacao(Transacao transacao) {
		return transacaoRepository.save(transacao);
	}
	
	
	// 7) Método para realizar uma transação
    
    public Transacao realizarTransacaoDebitar(int idConta, BigDecimal valor, 
    		int tipoTransacao, String descricaoTransacao, int temTarifa) {
    	
    	
    	 Conta conta = contaRepository.findByIdConta(idConta);

    	 // Atualizar o valor pretendido + valor da tarifa
    	 
    	 if(temTarifa != 0) {
    		 valor = tarifaServices.calcularTarifa(temTarifa, valor);
    	 }
    	 
    	// Executar o método 6 para ver se tem saldo suficiente
    	
        String resultado = contaService.verificarSaldoSuficiente(conta.getIdConta(), valor);
        
        if (resultado.equals("Saldo suficiente!")) {
        	
            // Caso tenha saldo, debita do valor da conta
        	
            conta.setSaldoConta(conta.getSaldoConta().subtract(valor)); 	// Subtrai o valor
            contaRepository.save(conta);  									// Atualiza o saldo da conta no banco de dados
            
            
            // Criação da instância Transação
            
            Transacao transacao = new Transacao();
            int tipoOperacao = 1; 								// 1 - DÉBITO
            LocalDateTime dataTransacao = LocalDateTime.now();
            
            transacao.setTipoTransacao(tipoTransacao);
            transacao.setValorTransacao(valor);
            transacao.setDataTransacao(dataTransacao);			// Atribui a data e hora atual
            transacao.setDescricaoTransacao(descricaoTransacao);
            transacao.setConta(conta);
            transacao.setTipoOperacao(tipoOperacao);
            
            int codOperacao = transacao.getIdTransacao();
            
            transacao.setCodOperacao(codOperacao);
            
            // Salvar transação no banco de dados
            transacaoRepository.save(transacao); 				
            
           
            return transacao;
            
            
        }
		return null;
    
    }
    
    
 // 7) Fazer transferência para outra conta

 	public Transacao transferirParaOutraConta(int idConta, int numeroAgenciaDestino, 
 			int numeroContaDestino, BigDecimal valor, String descricaoTransacao, int temTarifa) {

 		Transacao transferencia = new Transacao();

 		// Buscar a conta pelo número da conta para verificar se a Conta Origem existe
 		Conta contaOrigem = contaRepository.findByIdConta(idConta);

// 		// Verifica se a conta origem foi encontrada
// 		if (contaOrigem == null) {
// 		return "Conta Origem não encontrada!";
// 	} else {

 		// Buscar a conta pelo número da conta para verificar se a Conta Origem existe
 		Conta contaDestino = contaRepository.findByNumAgenciaAndNumeroConta(numeroAgenciaDestino, numeroContaDestino);

// 		// Verifica se a conta destino foi encontrada
// 		if (contaDestino == null) {
// 				return "Conta Destino não encontrada!";
// 			} else {
// 				
 		int tipoTransacao = 0; // Colocar como Tipo de Transação = Transferência

 		// Fazer a transação
 		transferencia = realizarTransacaoDebitar(
 				contaOrigem.getIdConta(), valor, tipoTransacao, descricaoTransacao, temTarifa);

 		// Creditar na Conta Destino
 		contaService.creditarContaDestino(contaDestino, transferencia);
 		
 		return transferencia;
 		
 	}
    
    
//    public Transacao transferirParaOutraConta(int idConta, int numeroAgenciaDestino, 
//            int numeroContaDestino, BigDecimal valor, String descricaoTransacao, int temTarifa) {
//
//        // Busca a conta origem
//        Conta contaOrigem = contaRepository.findByIdConta(idConta);
//        if (contaOrigem == null) {
//            return null;  // Conta origem não encontrada
//        }
//
//        // Busca a conta destino
//        Conta contaDestino = contaRepository.findByNumAgenciaAndNumeroConta(numeroAgenciaDestino, numeroContaDestino);
//        if (contaDestino == null) {
//            return null;  // Conta destino não encontrada
//        }
//
//        // Calcula o valor total com tarifa (apenas para a transação de débito na conta origem)
//        BigDecimal valorComTarifa = valor;
//        if (temTarifa != 0) {
//            valorComTarifa = tarifaServices.calcularTarifa(temTarifa, valor);
//        }
//
//        // Executa a transação de débito na conta origem
//        Transacao transferencia = realizarTransacaoDebitar(
//                contaOrigem.getIdConta(), valorComTarifa, 0, descricaoTransacao, temTarifa);
//
//        if (transferencia != null) {
//            // Credita na conta destino apenas o valor original (sem a tarifa)
//            creditarContaDestino(contaDestino, valor);
//            return transferencia;
//        }
//
//        return null;  
//    }

    public void creditarContaDestino(Conta contaDestino, BigDecimal valorCredito) {
        BigDecimal saldoAtual = contaDestino.getSaldoConta();
        BigDecimal novoSaldo = saldoAtual.add(valorCredito);
        contaDestino.setSaldoConta(novoSaldo);

        // Salva a conta destino com o novo saldo
        contaRepository.save(contaDestino);
    }

    
 	
 // Método para realizar uma transação de crédito (depósito)
 	public Transacao realizarTransacaoCreditar(int idConta, BigDecimal valor, 
 	        int tipoTransacao, String descricaoTransacao) {
 	    
 	    // Busca a conta de destino pelo idConta
 	    Conta conta = contaRepository.findByIdConta(idConta);
 	    
 	    // Verifica se a conta foi encontrada
 	    if (conta != null) {
 	        // Criação da instância Transação
 	        Transacao transacao = new Transacao();
 	        int tipoOperacao = 2;  // 2 - CRÉDITO (DEPÓSITO)
 	        LocalDateTime dataTransacao = LocalDateTime.now();
 	        
 	        // Atribui os dados da transação
 	        transacao.setTipoTransacao(tipoTransacao);
 	        transacao.setValorTransacao(valor);
 	        transacao.setDataTransacao(dataTransacao);  
 	        transacao.setDescricaoTransacao(descricaoTransacao);
 	        transacao.setConta(conta);
 	        transacao.setTipoOperacao(tipoOperacao);
 	        
 	        // Credita o valor na conta
 	        conta.setSaldoConta(conta.getSaldoConta().add(valor));  
 	        contaRepository.save(conta);  // Atualiza o saldo da conta no banco de dados
 	        
 	        // Salva a transação no banco de dados
 	        transacaoRepository.save(transacao);
 	        
 	        return transacao;
 	    }
 	    return null;  // Caso a conta não exista
 	}
 	

}
