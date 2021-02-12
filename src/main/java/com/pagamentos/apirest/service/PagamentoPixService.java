package com.pagamentos.apirest.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamentos.apirest.models.PagamentoPix;
import com.pagamentos.apirest.repository.PagamentoPixRepository;

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
	
//	public double calculaPorcentagem(Calendar data, double valor) {
//		List<PagamentoPix> pagamentos = this.listaPagamentos();
//		
//		int mêsVigente = data.get(Calendar.MONTH);
//		
//		double valorTotal = 0;
//		for (PagamentoPix pagamentoPix : pagamentos) {
//			if (pagamentoPix.getData().get(Calendar.MONTH) == mêsVigente) {
//				valorTotal += pagamentoPix.getValor();
//			}
//		}
//		double porcentagem = valor/valorTotal;
//		return porcentagem; 
//	}
	
}
