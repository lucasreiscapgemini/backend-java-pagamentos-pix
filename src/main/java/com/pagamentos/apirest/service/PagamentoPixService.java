package com.pagamentos.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamentos.apirest.repository.PagamentoPixRepository;
import com.pagamentos.apirest.model.PagamentoPix;

@Service
public class PagamentoPixService {
	
	@Autowired
	private PagamentoPixRepository pagamentoPixRepository;
	
	public List<PagamentoPix> listaPagamentos() {
		return pagamentoPixRepository.findAll();
	}
	
	public PagamentoPix listaPagamento(long id) {
		return pagamentoPixRepository.findById(id);
	}
	
	public void deletaPagamento(PagamentoPix pagamento) {
		pagamentoPixRepository.delete(pagamento);
	}
	
	public PagamentoPix salvaPagamento(PagamentoPix produto) {
		return pagamentoPixRepository.save(produto);
	}
	
	public PagamentoPix atualizaPagamento(PagamentoPix produto) {
		return pagamentoPixRepository.save(produto);
	}
	
	public void calculaPorcentagem(PagamentoPix pagamento) {
		// FALTA IMPLEMENTAR
	}
	
}
