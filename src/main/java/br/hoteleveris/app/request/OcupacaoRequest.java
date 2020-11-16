package br.hoteleveris.app.request;

import javax.xml.crypto.Data;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.model.Quarto;

public class OcupacaoRequest {
	private Cliente clienteId;
	private Quarto quartoId;
	private Double qtdDiarias;
	private String situacao;
	

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
