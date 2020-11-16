package br.hoteleveris.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Comodidade;
import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.model.QuartoComodidade;
import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.repository.ComodidadeRepository;
import br.hoteleveris.app.repository.QuartoComodidadeRepository;
import br.hoteleveris.app.repository.QuartoRepository;
import br.hoteleveris.app.repository.TipoQuartoRepository;
import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.request.QuartoComodidadeRequest;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.QuartoResponse;

@Service
public class QuartoService {
	@Autowired
	private QuartoRepository repository;
	
	@Autowired
	private QuartoComodidadeRepository quartoComodidadeRepository;
	
	public BaseResponse inserir(QuartoRequest request) {
		if(request.getAndar() == "")
			return new BaseResponse(404, "Preencha o andar.");
		
		if(request.getNumQuarto() <= 0)
			return new BaseResponse(404, "Preencha o numero do quarto");
		
		if(request.getSituacao() == "")
			return new BaseResponse(404, "Preencha a situação");
	
		TipoQuarto tipoQuarto = new TipoQuarto(request.getTipoQuartoId());
		Quarto quarto = new Quarto(
				request.getAndar(),
				request.getNumQuarto(),
				request.getSituacao(),
				tipoQuarto
				);
		
		repository.save(quarto);
		
		for(ComodidadeRequest item : request.getComodidade()) {
			QuartoComodidade quartoComodidade = new QuartoComodidade(
					new Quarto(quarto.getId()),
					new Comodidade(item.getId())
					);
						quartoComodidadeRepository.save(quartoComodidade);
		}
		
		return new BaseResponse(201, "Quarto Salvo.");
	}

	public QuartoResponse obter(Long id) {
		Optional<Quarto> quarto = repository.findById(id);
		QuartoResponse response = new QuartoResponse();
		
		if (!quarto.isPresent()) {
			response.message = "Id invalido.";
			response.statusCode = 404;
			return response; 
		}
			
		response.setId(quarto.get().getId());
		response.setAndar(quarto.get().getAndar());
		response.setTipoQuarto(quarto.get().getTipoQuarto());
		response.setSituacao(quarto.get().getSituacao());
		response.setNumQuarto(quarto.get().getNumQuarto());

		
		new BaseResponse(200, "Obtido com sucesso");
		return response;
	}

	public BaseResponse atualizar(Long id, QuartoRequest request) {
		Optional<Quarto> quarto = repository.findById(id);
		
		if(!quarto.isPresent())
			return new BaseResponse(404, "Nao existe o quarto com esse id.");
		
		if (request.getSituacao() == "") 
			return new BaseResponse(404, "Preencha o campo situação ");
	
		quarto.get().setSituacao(request.getSituacao());

		repository.save(quarto.get());
		
		return new BaseResponse(200, "Atualizado com sucesso ");

	}
}
