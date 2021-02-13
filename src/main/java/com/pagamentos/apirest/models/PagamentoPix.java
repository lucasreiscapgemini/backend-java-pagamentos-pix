package com.pagamentos.apirest.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="TB_PAGAMENTO")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"data"},
allowGetters = true)
public class PagamentoPix implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nomeDestinatario;
	
	private String cpf;
	
	private String instituicaoBancaria;
	
	private String chavePix;
	
	private double valor;
	
	private String descricao;
	
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date data;
	
	private double porcentagem;
	
	public PagamentoPix() {
		
	}
	
	public PagamentoPix(String nomeDestinatario, String cpf, String instituicaoBancaria, String chavePix,
			double valor, String descricao) {
		this.nomeDestinatario = nomeDestinatario;
		this.cpf = cpf;
		this.instituicaoBancaria = instituicaoBancaria;
		this.chavePix = chavePix;
		this.valor = valor;
		this.descricao = descricao;
		this.porcentagem = 0;
		this.data = Calendar.getInstance().getTime();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
