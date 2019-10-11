package br.com.grupoabril.teste.spring.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;


@Entity
public class Assinatura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoAssinatura; 
	
	@ManyToOne()
	@JoinColumn(name = "codigoCliente", nullable = false)
	private Cliente cliente;
	
	@ManyToOne()
	@JoinColumn(name = "codigoProduto", nullable = false)
	private Produto produto;

	@ColumnDefault(value = "true")
	private Boolean ativo;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getCodigoAssinatura() {
		return codigoAssinatura;
	}

	public void setCodigoAssinatura(Integer codigoAssinatura) {
		this.codigoAssinatura = codigoAssinatura;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}