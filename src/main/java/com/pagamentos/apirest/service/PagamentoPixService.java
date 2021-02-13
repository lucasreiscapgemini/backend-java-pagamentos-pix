package com.pagamentos.apirest.service;

import java.util.Calendar;
import java.util.Date;
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
		return pagamentoPixRepository.findById(id).get();
	}
	
	public void deletaPagamento(PagamentoPix pagamento) {
		pagamentoPixRepository.delete(pagamento);
	}
	
	public PagamentoPix salvaPagamento(PagamentoPix produto) {
		produto.setData(new Date());
		produto.setPorcentagem(calculaPorcentagem(produto.getValor()));
		return pagamentoPixRepository.save(produto);
	}
	
	public PagamentoPix atualizaPagamento(PagamentoPix produto) {
		produto.setPorcentagem(calculaPorcentagem(produto.getValor()));
		return pagamentoPixRepository.save(produto);
	}
	
	public void deletaPagamento(Long id) {
		this.pagamentoPixRepository.deleteById(id);
	}
	
	public double calculaPorcentagem(double valor) {
		List<PagamentoPix> pagamentos = this.listaPagamentos();
		
		int mesVigente = Calendar.getInstance().get(Calendar.MONTH);
		
		double valorTotal = 0;
		for (PagamentoPix pagamentoPix : pagamentos) {
			if (pagamentoPix.getData().getMonth() == mesVigente) {
				valorTotal += pagamentoPix.getValor();
			}
		}
		double porcentagem = valor/valorTotal;
		return porcentagem; 
	}
	
}
