package br.hoteleveris.app.service;

import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ComodidadeResponse;

public interface ComodidadeService {
	public BaseResponse inserir(ComodidadeRequest request);
	
	public ComodidadeResponse obter(Long id);
}
