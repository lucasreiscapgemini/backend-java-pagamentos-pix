package com.capgemini.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.capgemini.model.entity.PagamentoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PagamentoDTO {

	public PagamentoDTO(PagamentoEntity pagamento, BigDecimal porcentagem) {
		this.id = pagamento.getId();
		this.nomeDestinatario = pagamento.getNomeDestinatario();
		this.cpf = pagamento.getCpf();
		this.chavePix = pagamento.getChavePix();
		this.valor = pagamento.getValor();
		this.dataPagamento = pagamento.getDataPagamento();
		this.descricao = pagamento.getDescricao();
		this.porcentagem = porcentagem;
	}

	private Long id;
	private String nomeDestinatario;
	private String cpf;
	private String chavePix;
	private BigDecimal valor;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataPagamento;
	private String descricao;
	private BigDecimal porcentagem;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDestinatario() {
		return this.nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getChavePix() {
		return this.chavePix;
	}

	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPorcentagem() {
		return this.porcentagem;
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}

}
