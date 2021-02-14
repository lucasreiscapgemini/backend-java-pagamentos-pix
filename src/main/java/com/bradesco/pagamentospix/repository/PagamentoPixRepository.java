package com.bradesco.pagamentospix.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bradesco.pagamentospix.model.PagamentoPix;

public interface PagamentoPixRepository extends JpaRepository<PagamentoPix, Long> {
	
	// Retorna todos os pagamentos do mês passado como parâmetro
	@Query(value = "SELECT * FROM PAGAMENTO_PIX WHERE MONTH(DATA_HORA) = ?1", nativeQuery = true)
	public List<PagamentoPix> findAllByMes(int mes);
	
	// Retorna todos os valores dos pagamentos realizados no mês passado como parâmetro
	@Query(value = "SELECT VALOR FROM PAGAMENTO_PIX WHERE MONTH(DATA_HORA) = ?1", nativeQuery = true)
	public List<BigDecimal> findAllValoresByMes(int mes);
}
