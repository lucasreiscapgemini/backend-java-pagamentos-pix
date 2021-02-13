package com.pagamentos.apirest.models.dto;

import java.math.BigDecimal;

public class PagamentoPixDTO {
	
	private long id;
	
	private String nomeDestinatario;
	
	private String cpf;
	
	private String instituicaoBancaria;
	
	private String chavePix;
	
	private BigDecimal valor;
	
	private String descricao;
	
	private String data;
	
	private String porcentagem;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getInstituicaoBancaria() {
		return instituicaoBancaria;
	}

	public void setInstituicaoBancaria(String instituicaoBancaria) {
		this.instituicaoBancaria = instituicaoBancaria;
	}

	public String getChavePix() {
		return chavePix;
	}

	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PagamentoPixDTO(long id, String nomeDestinatario, String cpf, String instituicaoBancaria, String chavePix,
			BigDecimal valor, String descricao, String data, String porcentagem) {
		super();
		this.id = id;
		this.nomeDestinatario = nomeDestinatario;
		this.cpf = cpf;
		this.instituicaoBancaria = instituicaoBancaria;
		this.chavePix = chavePix;
		this.valor = valor;
		this.descricao = descricao;
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
