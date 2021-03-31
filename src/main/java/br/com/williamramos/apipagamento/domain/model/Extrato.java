package br.com.williamramos.apipagamento.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Extrato {
    private List<Pagamento> listaPagamentos;
    private BigDecimal totalPagamento;

    public Extrato() {
        this.listaPagamentos = new ArrayList<>();

    }

    public List<Pagamento> getListaPagamentos() {
        return listaPagamentos;
    }

    public void setListaPagamentos(List<Pagamento> listaPagamentos) {
        this.listaPagamentos = listaPagamentos;
    }

    public BigDecimal getTotalPagamento() {
       return this.totalPagamento;
    }

    public void setTotalPagamento(BigDecimal totalPagamento) {
        this.totalPagamento = totalPagamento;
    }


}
