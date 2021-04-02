package br.com.williamramos.apipagamento.domain.model;

import br.com.williamramos.apipagamento.domain.enus.Bancos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "tb_pagamento")
@Table(name = "tb_pagamento")
public class Pagamento extends BaseEntity {
    @Column(name = "destinatario")
    private String destinatario;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "chave_pix")
    private String chavePix;

    @Column(name = "instituicao_bancaria")
    private Bancos instituicaoBancaria;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "descricao")
    private String Descricao;

    @Transient
    private Double porcentagem;



    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public Bancos getInstituicaoBancaria() {
        return instituicaoBancaria;
    }

    public void setInstituicaoBancaria(Bancos instituicaoBancaria) {
        this.instituicaoBancaria = instituicaoBancaria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }
}
