package br.hoteleveris.app.service;

import br.hoteleveris.app.request.QuartoPatchRequest;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.QuartoResponse;

public interface QuartoService {
	public BaseResponse inserir(QuartoRequest request);

	public QuartoResponse obter(Long id);

	public BaseResponse atualizar(Long id, QuartoPatchRequest request);
}
