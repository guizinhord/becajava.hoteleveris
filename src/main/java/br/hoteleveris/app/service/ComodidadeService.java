package br.hoteleveris.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Comodidade;
import br.hoteleveris.app.repository.ComodidadeRepository;
import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ComodidadeResponse;

@Service
public class ComodidadeService {

	@Autowired
	private ComodidadeRepository repository;

	public BaseResponse inserir(ComodidadeRequest request) {
		Comodidade comodidade = new Comodidade();
		BaseResponse base = new BaseResponse();
		base.statusCode = 400;

		if (request.getNome() == "" || request.getNome() == null) {
			base.message = "Preencha o campo nome.";
			return base;
		}
		comodidade.setNome(request.getNome());

		repository.save(comodidade);
		base.message = "Comodidade salva.";
		base.statusCode = 201;
		return base;
	}

	public ComodidadeResponse obter(Long id) {
		Optional<Comodidade> comodidade = repository.findById(id);
		ComodidadeResponse response = new ComodidadeResponse();
		

		if (comodidade.isEmpty()) {
			response.message = "Nao encontrado";
			response.statusCode = 404;
		}
		response.setId(comodidade.get().getId());
		response.setNome(comodidade.get().getNome());

		response.message = "Obtido com sucesso";
		response.statusCode = 200;
		return response;

	}
}
