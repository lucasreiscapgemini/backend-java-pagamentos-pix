package com.pagamentos.apirest.models.dto;

import java.math.BigDecimal;

public class PagamentoPixDTO {
	
	private String nomeDestinatario;
	
	private BigDecimal valor;
	
	private String data;
	
	private String porcentagem;
	
	public PagamentoPixDTO(String nomeDestinatario, BigDecimal valor, String data, String porcentagem) {
		super();
		this.nomeDestinatario = nomeDestinatario;
		this.valor = valor;
		this.data = data;
		this.porcentagem = porcentagem;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}

	
	
	
}
