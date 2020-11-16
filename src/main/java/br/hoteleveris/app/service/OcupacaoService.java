package br.hoteleveris.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.repository.OcupacaoRepository;
import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListOcupacao;

@Service
public class OcupacaoService {
	@Autowired
	private OcupacaoRepository repository;
	
	public BaseResponse inserir(OcupacaoRequest request) {
		if(request.getClienteId() == null)
			return new BaseResponse(404, "Preencha id do Cliente.");
		if(request.getQuartoId() == null)
			return new BaseResponse(404, "Preencha id do Quarto.");
		
		if(request.getQtdDiarias() <= 0)
			return new BaseResponse(404,"Preencha qts de diarias.");
		if(request.getSituacao() == "")
			return new BaseResponse(404, "Preencha situacao");
		
		request.setSituacao("N");
		Ocupacao ocupacao = new Ocupacao(
				request.getClienteId(),
				request.getQuartoId(),
				request.getQtdDiarias(),
				request.getSituacao()
				);
		
		repository.save(ocupacao);
		return new BaseResponse(201, "Ocupacao Salva.");	
	}
	
	public ListOcupacao listar() {
		List<Ocupacao> lista = repository.findAll();
		ListOcupacao response = new ListOcupacao();
		response.setOcupacoes(lista);
		
		response.statusCode = 200;
		response.message = "Ocupaçoes obtidas";
		return response;
	}
}
