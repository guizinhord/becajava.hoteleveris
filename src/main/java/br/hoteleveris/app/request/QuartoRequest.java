package br.hoteleveris.app.request;

import java.util.List;

import br.hoteleveris.app.model.TipoQuarto;

public class QuartoRequest {
	private String andar;
	private int numQuarto;
	private String situacao;
	private Long tipoQuartoId;

	private List<ComodidadeRequest> comodidade;

	public Long getTipoQuartoId() {
		return tipoQuartoId;
	}

	public void setTipoQuartoId(Long tipoQuartoId) {
		this.tipoQuartoId = tipoQuartoId;
	}

	public List<ComodidadeRequest> getComodidade() {
		return comodidade;
	}

	public void setComodidade(List<ComodidadeRequest> comodidade) {
		this.comodidade = comodidade;
	}

	public String getAndar() {
		return andar;
	}

	public void setAndar(String andar) {
		this.andar = andar;
	}

	public int getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(int numQuarto) {
		this.numQuarto = numQuarto;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
