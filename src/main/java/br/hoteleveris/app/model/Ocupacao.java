package br.hoteleveris.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.crypto.Data;

@Entity
public class Ocupacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "clienteId")
	private Cliente clienteId;
	@ManyToOne
	@JoinColumn(name = "quartoId")
	private Quarto quartoId;

	private Double qtdDiarias;
	private String situacao;
	// private Data data;

	public Ocupacao(Cliente clienteId, Quarto quartoId, Double qtdDiarias, String situacao/* , Data data */) {
		super();
		this.clienteId = clienteId;
		this.quartoId = quartoId;
		this.qtdDiarias = qtdDiarias;
		this.situacao = situacao;
	}

	public Ocupacao() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getClienteId() {
		return clienteId;
	}

	public void setClienteId(Cliente clienteId) {
		this.clienteId = clienteId;
	}

	public Quarto getQuartoId() {
		return quartoId;
	}

	public void setQuartoId(Quarto quartoId) {
		this.quartoId = quartoId;
	}

	public Double getQtdDiarias() {
		return qtdDiarias;
	}

	public void setQtdDiarias(Double qtdDiarias) {
		this.qtdDiarias = qtdDiarias;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
