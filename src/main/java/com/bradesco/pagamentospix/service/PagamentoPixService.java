package com.bradesco.pagamentospix.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.pagamentospix.model.PagamentoPix;
import com.bradesco.pagamentospix.repository.PagamentoPixRepository;

@Service
public class PagamentoPixService {

	@Autowired
	private PagamentoPixRepository repository;
	
	private double somatorioValores;
	
	// Retorna pagamentos do mês atual
	public List<PagamentoPix> listarPagamentosPorMes() {
		int mes = receberMesAtual();
		return repository.findAllByMes(mes);
	}
	
	// Atribui data e hora atuais ao pagamento, persiste-o e 
	// atualiza os percentuais para cada pagamento registrado
	public void salvar(PagamentoPix pagamento) {
		this.atribuirDataHoraAtualAoPagamento(pagamento);
		repository.save(pagamento);

	}
	
	// Remove pagamento pelo id e atualiza os percentuais
	// para cada pagamento registrado no mês atual
	public void remover(Long id) {
		repository.deleteById(id);
		this.atualizarPercentuaisPorMes();
	}
	
	// Atauliza pagamento e atualiza os percentuais
	// para cada pagamento registrado no mês atual
	public void atualizar(PagamentoPix pagamento) {
		PagamentoPix pagamentoRegistrado = repository.findById(pagamento.getId()).get();
		pagamentoRegistrado.setNomeDestinatario(pagamento.getNomeDestinatario());
		pagamentoRegistrado.setChavePix(pagamento.getChavePix());
		pagamentoRegistrado.setCpf(pagamento.getCpf());
		pagamentoRegistrado.setDataHora(new Date());
		pagamentoRegistrado.setDescricao(pagamento.getDescricao());
		pagamentoRegistrado.setInstituicaoBancaria(pagamento.getInstituicaoBancaria());
		pagamentoRegistrado.setValor(pagamento.getValor());
		repository.save(pagamentoRegistrado);
		this.atualizarPercentuaisPorMes();
	}
	
	// Atribui data e horas atuais ao pagamento efetuado
	public void atribuirDataHoraAtualAoPagamento(PagamentoPix pagamento) {
		Date dataHora = new Date();
		pagamento.setDataHora(dataHora);
	}
	
	// Retorna o mês atual dentre os valores que vão de 1 ... 12
	public int receberMesAtual() {
		Calendar calendario = Calendar.getInstance();
		int mes = calendario.get(Calendar.MONTH);
		// Incrementa em uma unidade o índice do mês retornado para
		// que acesse corretamente o valor do mês no banco
		++mes;
		return mes;
	}
	
	// Retorna todos os valores dos pagamentos do mês atual
	public List<BigDecimal> receberValoresDoMes() {
		int mes = receberMesAtual();
		List<BigDecimal> valores = repository.findAllValoresByMes(mes);
		return valores;
	}
	
	// Atualiza os percentuais de cada pagamento
	public void atualizarPercentuaisPorMes() {
		List<PagamentoPix> pagamentos = repository.findAll();
		pagamentos.forEach((pagamento) -> {
			pagamento.setPercentualPorMes(definirDecimaisParaPercentualPorMes(calcularPercentualPorMes(pagamento)));
			salvar(pagamento);
		});
	}
	
	// Calcular o percentual de cada pagamento 
	// com base no montante total do mês atual
	public double calcularPercentualPorMes(PagamentoPix pagamento) {
		somatorioValores = 0;
		List<BigDecimal> valores = this.receberValoresDoMes();
		valores.forEach((valor) -> {
			somatorioValores = somatorioValores + valor.doubleValue();
		});
		double percentualPorMes = (pagamento.getValor().doubleValue()/somatorioValores);
		return percentualPorMes;
	}
	
	// Define formato do percentual do pagamento
	public double definirDecimaisParaPercentualPorMes(double percentualPorMes) {
		percentualPorMes = percentualPorMes*100;
		BigDecimal bigDecimal = BigDecimal.valueOf(percentualPorMes);
		bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
		return bigDecimal.doubleValue();
	}
}
