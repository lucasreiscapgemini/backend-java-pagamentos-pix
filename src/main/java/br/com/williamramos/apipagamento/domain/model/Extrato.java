package br.com.williamramos.apipagamento.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Extrato {
    private List<Pagamento> listaPagamentos;
    private double totalPagamento;

    public Extrato() {
        this.listaPagamentos = new ArrayList<>();

    }

    public List<Pagamento> getListaPagamentos() {
        return listaPagamentos;
    }

    public void setListaPagamentos(List<Pagamento> listaPagamentos) {
        this.listaPagamentos = listaPagamentos;
        this.calcularPorcentagemPagamento();
    }

    public Double getTotalPagamento() {
        return this.totalPagamento;
    }

    public void setTotalPagamento(Double totalPagamento) {
        this.totalPagamento = totalPagamento;
    }

    private void calcularPorcentagemPagamento() {
        this.listaPagamentos.forEach(item -> {
            this.totalPagamento += item.getValor();
        });
        this.listaPagamentos.forEach(item -> {
            double porc = (item.getValor() / totalPagamento) * 100;
            item.setPorcentagem(porc);
        });
    }


}
