package com.pagamentos.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagamentos.apirest.models.PagamentoPix;

@Repository
public interface PagamentoPixRepository extends JpaRepository<PagamentoPix, Long>{

}
