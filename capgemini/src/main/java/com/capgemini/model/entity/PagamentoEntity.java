package com.capgemini.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "pagamento")
public class PagamentoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	@NotBlank
	@Size(min = 1, max = 100)
	private String nomeDestinatario;
	@Size(max = 11)
	@Pattern(regexp = "^[0-9]{11}$", message = "{validation.CPF}")
	@NotBlank
	private String cpf;
	@NotBlank
	private String chavePix;
	@NotNull
	private BigDecimal valor;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(columnDefinition = "timestamp default now()", nullable = false)
	private LocalDateTime dataPagamento;
	private String descricao;

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

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof PagamentoEntity)) {
			return false;
		}

		PagamentoEntity pagamentoEntity = (PagamentoEntity) o;

		return Objects.equals(this.id, pagamentoEntity.id)
				&& Objects.equals(this.nomeDestinatario, pagamentoEntity.nomeDestinatario)
				&& Objects.equals(this.cpf, pagamentoEntity.cpf)
				&& Objects.equals(this.chavePix, pagamentoEntity.chavePix)
				&& Objects.equals(this.valor, pagamentoEntity.valor)
				&& Objects.equals(this.dataPagamento, pagamentoEntity.dataPagamento)
				&& Objects.equals(this.descricao, pagamentoEntity.descricao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.cpf);
	}

}
