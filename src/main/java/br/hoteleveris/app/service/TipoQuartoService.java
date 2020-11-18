package br.hoteleveris.app.service;

import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListTipoQuarto;
import br.hoteleveris.app.response.TipoQuartoResponse;

public interface TipoQuartoService {
	public BaseResponse inserir(TipoQuartoRequest request);

	public TipoQuartoResponse obter(Long id);

	public ListTipoQuarto listar();
}
