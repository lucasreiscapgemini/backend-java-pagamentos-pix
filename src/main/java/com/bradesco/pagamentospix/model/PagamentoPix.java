package com.bradesco.pagamentospix.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class PagamentoPix {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nomeDestinatario;
	
	@NotNull
	@CPF(message="CPF inválido")
	private String cpf;
	
	@NotNull
	private String instituicaoBancaria;
	
	@NotNull
	private String chavePix;
	
	@NumberFormat(pattern = "#,##0.00")
	@NotNull
	private BigDecimal valor;
	
	private String descricao;
	
	@DateTimeFormat(pattern="dd/MM/yyy HH:mm")
	private Date dataHora;
	
	private double percentualPorMes;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public double getPercentualPorMes() {
		return percentualPorMes;
	}

	public void setPercentualPorMes(double percentualPorMes) {
		this.percentualPorMes = percentualPorMes;
	}
	
}
