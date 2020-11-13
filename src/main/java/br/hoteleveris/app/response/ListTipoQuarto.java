package br.hoteleveris.app.response;

import java.util.List;

import br.hoteleveris.app.model.TipoQuarto;

public class ListTipoQuarto extends BaseResponse{
	private List<TipoQuarto> tipoQuartos;

	public List<TipoQuarto> getTipoQuartos() {
		return tipoQuartos;
	}

	public void setTipoQuartos(List<TipoQuarto> tipoQuartos) {
		this.tipoQuartos = tipoQuartos;
	}
	
	
}
