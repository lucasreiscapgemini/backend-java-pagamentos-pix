package com.pagamentos.apirest.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
		return this.atualizaPercentualPagamentoMesAtual(pagamentoPixRepository.save(produto));
	}
	
	public PagamentoPix atualizaPagamento(PagamentoPix pagamento) {
		return this.atualizaPercentualPagamentoMesAtual(pagamentoPixRepository.save(pagamento));

	}
	
	public void deletaPagamento(Long id) {
		this.pagamentoPixRepository.deleteById(id);
	}
	
	public double calculaPorcentagem(BigDecimal valor) {

		List<PagamentoPix> pagamentos = this.listaPagamentos();
		
		int mesVigente = Calendar.getInstance().get(Calendar.MONTH);
		
		BigDecimal valorTotal = new BigDecimal(0.0);
		for (PagamentoPix pagamentoPix : pagamentos) {
			if (pagamentoPix.getData().getMonth() == mesVigente) {
				valorTotal.add(pagamentoPix.getValor());
			}
		}
		double porcentagem = valor.divide(valorTotal).doubleValue();
		return porcentagem; 
	}

	private BigDecimal obterValotTotal(){
		BigDecimal retorno = new BigDecimal(0.0);
		for(PagamentoPix p : this.listaPagamentos()){
			if(Objects.nonNull(p.getValor()) && (p.getData().getMonth() == Calendar.getInstance().get(Calendar.MONTH)))
				retorno = retorno.add(p.getValor());

		}
		return retorno;
	}

	private PagamentoPix atualizaPercentualPagamentoMesAtual(PagamentoPix pagamento ) {
		List<PagamentoPix> pagamentos = this.listaPagamentos();
		BigDecimal valorTotal = this.obterValotTotal();
		int mesVigente = Calendar.getInstance().get(Calendar.MONTH);


		if(!pagamentos.isEmpty()) {
			for (PagamentoPix pagamentoPix : pagamentos) {
				if (pagamentoPix.getData().getMonth() == mesVigente) {
					pagamentoPix.setPorcentagem(pagamentoPix.getValor().divide(valorTotal, MathContext.DECIMAL32).doubleValue());
				}
			}
			this.pagamentoPixRepository.saveAll(pagamentos);
			pagamento = pagamentos.get(pagamentos.lastIndexOf(pagamento));

		}else{
			pagamento.setPorcentagem(1);
		}

		return pagamento;
	}
	
}