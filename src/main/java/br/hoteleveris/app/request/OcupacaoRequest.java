package br.hoteleveris.app.request;

import javax.xml.crypto.Data;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.model.Quarto;

public class OcupacaoRequest {
	private Long clienteId;
	private Long quartoId;
	private double qtdDiarias;
	private String situacao;

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getQuartoId() {
		return quartoId;
	}

	public void setQuartoId(Long quartoId) {
		this.quartoId = quartoId;
	}

	public double getQtdDiarias() {
		return qtdDiarias;
	}

	public void setQtdDiarias(double qtdDiarias) {
		this.qtdDiarias = qtdDiarias;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
