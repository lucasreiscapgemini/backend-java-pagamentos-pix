package com.pagamentos.apirest.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.pagamentos.apirest.exception.PagamentoPixException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamentos.apirest.models.PagamentoPix;
import com.pagamentos.apirest.models.dto.PagamentoPixDTO;
import com.pagamentos.apirest.repository.PagamentoPixRepository;
import com.pagamentos.apirest.util.FormatacaoUtil;

@Service
public class PagamentoPixService {
	
	@Autowired
	private PagamentoPixRepository pagamentoPixRepository;
	
	public List<PagamentoPixDTO> listaPagamentos() {
		return pagamentoPixRepository.findAll().stream().map(
				e -> entityToDto(e))
				.collect(Collectors.toList());
	}
	
	public PagamentoPixDTO exibePagamento(long id) {
		if(Objects.isNull(id) || !this.pagamentoPixRepository.existsById(id)){
			throw new PagamentoPixException("Pagamento Pix não é válido ou não existe");
		}
		return entityToDto(pagamentoPixRepository.findById(id).get());
	}
	
	public PagamentoPixDTO salvaPagamento(PagamentoPix pagamento) {
		if(Objects.isNull(pagamento.getId())){
			throw new PagamentoPixException("Pagamento Pix não pode ser salvo");
		}
		pagamento.setData(new Date());
		return this.atualizaPercentualPagamentoMesAtual(pagamentoPixRepository.save(pagamento));
	}
	
	public PagamentoPixDTO atualizaPagamento(PagamentoPix pagamento) {
		if(Objects.isNull(pagamento.getId()) || !this.pagamentoPixRepository.existsById(pagamento.getId())){
			throw new PagamentoPixException("Pagamento Pix não pode ser atualizado");
		}
		return this.atualizaPercentualPagamentoMesAtual(pagamentoPixRepository.save(pagamento));
	}
	
	public void deletaPagamento(Long id) {
		if(Objects.isNull(id) || !this.pagamentoPixRepository.existsById(id)) {
			this.pagamentoPixRepository.deleteById(id);
		}
		throw new PagamentoPixException("Pagamento Pix não é válido");
	}
	
	public void deletaPagamento(PagamentoPix pagamento) {
		if(Objects.isNull(pagamento.getId()) || !this.pagamentoPixRepository.existsById(pagamento.getId())){
			throw new PagamentoPixException("Pagamento Pix não é válido");
		}
		this.deletaPagamento(pagamento.getId());
	}
	
	public double calculaPorcentagem(BigDecimal valor) {

		List<PagamentoPix> pagamentos = pagamentoPixRepository.findAll();
		
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
		for(PagamentoPix p : pagamentoPixRepository.findAll()){
			if(Objects.nonNull(p.getValor()) && (p.getData().getMonth() == Calendar.getInstance().get(Calendar.MONTH)))
				retorno = retorno.add(p.getValor());

		}
		return retorno;
	}

	private PagamentoPixDTO atualizaPercentualPagamentoMesAtual(PagamentoPix pagamento ) {
		List<PagamentoPix> pagamentos = pagamentoPixRepository.findAll();
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

		return this.entityToDto(pagamento);
	}
	
	private PagamentoPixDTO entityToDto(PagamentoPix pagamentoPix) {
		return new PagamentoPixDTO(pagamentoPix.getNomeDestinatario(), pagamentoPix.getValor(), FormatacaoUtil.obterDataFormatada(pagamentoPix.getData()), FormatacaoUtil.obterPorcentagemFormatada(pagamentoPix.getPorcentagem()));
	}
	
	
}