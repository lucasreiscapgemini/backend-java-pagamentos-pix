package com.pagamentos.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pagamentos.apirest.models.PagamentoPix;

public interface PagamentoPixRepository extends JpaRepository<PagamentoPix, Long>{
	
	PagamentoPix findById(long id);
	
	List<PagamentoPix> findAll();

}
