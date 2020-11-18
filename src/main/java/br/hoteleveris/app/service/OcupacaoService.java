package br.hoteleveris.app.service;

import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListOcupacao;

public interface OcupacaoService {
	public BaseResponse inserir(OcupacaoRequest request);
	
	public ListOcupacao listar();
}
