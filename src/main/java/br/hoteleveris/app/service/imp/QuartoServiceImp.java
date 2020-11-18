package br.hoteleveris.app.service.imp;

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
import br.hoteleveris.app.request.QuartoPatchRequest;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.QuartoResponse;
import br.hoteleveris.app.service.QuartoService;

@Service
public class QuartoServiceImp implements QuartoService {
	@Autowired
	private QuartoRepository repository;

	@Autowired
	private QuartoComodidadeRepository quartoComodidadeRepository;

	public BaseResponse inserir(QuartoRequest request) {
		if (request.getAndar() == "" || request.getAndar() == null)
			return new BaseResponse(404, "Preencha o andar.");

		if (request.getNumQuarto() <= 0)
			return new BaseResponse(404, "Preencha o numero do quarto");

		if (request.getSituacao() == "" || request.getSituacao() == null)
			return new BaseResponse(404, "Preencha a situação");

		Quarto quarto = new Quarto();
		quarto.setAndar(request.getAndar());
		quarto.setNumQuarto(request.getNumQuarto());
		quarto.setSituacao(request.getSituacao());

		TipoQuarto tipoQuarto = new TipoQuarto(request.getTipoQuartoId());
		tipoQuarto.setId(request.getTipoQuartoId());
		quarto.setTipoQuarto(tipoQuarto);

		repository.save(quarto);

		for (ComodidadeRequest item : request.getComodidade()) {
			QuartoComodidade quartoComodidade = new QuartoComodidade(new Quarto(quarto.getId()),
					new Comodidade(item.getId()));
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

		response.message = "Quartos obtidos";
		response.statusCode = 200;
		return response;

	}

	public BaseResponse atualizar(Long id, QuartoPatchRequest request) {
		Optional<Quarto> quarto = repository.findById(id);
		BaseResponse response = new BaseResponse();

		if (!quarto.isPresent())
			return new BaseResponse(404, "Nao existe o quarto com esse id.");

		if (request.getSituacao() == "")
			return new BaseResponse(404, "Preencha o campo situação ");

		quarto.get().setSituacao(request.getSituacao());

		repository.save(quarto.get());
		response.message = "Atualizado com sucesso ";
		response.statusCode = 200;

		return response;

	}
}
