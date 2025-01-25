package com.avanade.projeto.fintech.trustbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avanade.projeto.fintech.trustbank.entities.Boleto;

public interface BoletoRepository extends JpaRepository <Boleto, Integer> {

	
		// 1) Consulta - Verificar se Boleto existe
	
		Boleto findByCodigoBoleto(String codigoBoleto);
		
		// 2) Consulta - Exibir boletos e seus pagamentos pelo ID_CONTA
		
		@Query(value = "SELECT  b.ID_BOLETO, \r\n"
				+ "		t.ID_CONTA, \r\n"
				+ "		b.CODIGO_BOLETO,\r\n"
				+ "		b.DATA_VENCIMENTO, \r\n"
				+ "		b.ID_TRANSACAO, \r\n"
				+ "		b.VALOR_BOLETO,\r\n"
				+ "		t.VALOR AS 'VALOR PAGO', \r\n"
				+ "		t.DATA_TRANSACAO AS 'DATA DO PAGAMENTO'\r\n"
				+ "		FROM BOLETO b INNER JOIN TRANSACAO t \r\n"
				+ "		ON b.ID_TRANSACAO = t.ID_TRANSACAO WHERE t.ID_CONTA = :valoridconta", nativeQuery = true)
		List<Boleto> listarBoletosPagos(@Param("valoridconta") int valoridconta);
		
		
}
