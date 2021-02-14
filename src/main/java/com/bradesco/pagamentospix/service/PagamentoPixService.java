package com.bradesco.pagamentospix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.pagamentospix.model.PagamentoPix;
import com.bradesco.pagamentospix.repository.PagamentoPixRepository;

@Service
public class PagamentoPixService {

	@Autowired
	private PagamentoPixRepository repository;
	
	public void salvar(PagamentoPix pagamento) {
		repository.save(pagamento);
	}
}
