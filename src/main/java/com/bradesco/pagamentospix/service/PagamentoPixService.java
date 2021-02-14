package com.bradesco.pagamentospix.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.pagamentospix.model.PagamentoPix;
import com.bradesco.pagamentospix.repository.PagamentoPixRepository;

@Service
public class PagamentoPixService {

	@Autowired
	private PagamentoPixRepository repository;
	
	private double somatorioValores;
	
	public List<PagamentoPix> listarPagamentosPorMes() {
		int mes = receberMesAtual();
		
		return repository.findAllByMes(mes);
	}
	
	public void salvar(PagamentoPix pagamento) {
		repository.save(pagamento);
	}
	
	public void atribuirDataHoraAtual(PagamentoPix pagamento) {
		Date dataHora = new Date();
		pagamento.setDataHora(dataHora);
	}
	
	public int receberMesAtual() {
		Calendar calendario = Calendar.getInstance();
		int mes = calendario.get(Calendar.MONTH);
		// incrementando em uma unidade o índice do mês retornado para que acesse corretamente o valor do mês no banco
		++mes;
		return mes;
	}
	
	public List<BigDecimal> receberValoresDoMes() {
		int mes = receberMesAtual();
		List<BigDecimal> valores = repository.findAllValoresByMes(mes);
		return valores;
	}
	
	public void atualizarPercentuaisPorMes() {
		List<PagamentoPix> pagamentos = repository.findAll();
		pagamentos.forEach((pagamento) -> {
			pagamento.setPercentualPorMes(definirDecimaisParaPercentualPorMes(calcularPercentualPorMes(pagamento)));
			salvar(pagamento);
		});
	}
	
	public double calcularPercentualPorMes(PagamentoPix pagamento) {
		somatorioValores = 0;
		List<BigDecimal> valores = receberValoresDoMes();
//		valores.forEach((valor) -> {
//			somatorioValores = somatorioValores + valor.doubleValue();
//		});
		
		for (BigDecimal valor : valores) {
			somatorioValores = valor.doubleValue() + somatorioValores;
		}
		double percentualPorMes = (pagamento.getValor().doubleValue()/somatorioValores);
		return percentualPorMes;
	}
	
	public double definirDecimaisParaPercentualPorMes(double percentualPorMes) {
		percentualPorMes = BigDecimal.valueOf(percentualPorMes).setScale(3, RoundingMode.HALF_UP).doubleValue();
		percentualPorMes = percentualPorMes*100;
		return percentualPorMes;
	}
}
