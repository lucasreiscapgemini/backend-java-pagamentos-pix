package br.com.williamramos.apipagamento.domain.repository;

import br.com.williamramos.apipagamento.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByCpf(String cpf);

    List<Pagamento> findByChavePix(String chavePix);

    @Query("select p from tb_pagamento p where p.dataPagamento between :inicio and :fim and p.cpf =:cpf")
    List<Pagamento> findByDataPagamentoBetweenAndCpf(LocalDate inicio, LocalDate fim, String cpf);

}
