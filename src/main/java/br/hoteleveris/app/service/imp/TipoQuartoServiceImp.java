package br.hoteleveris.app.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.repository.TipoQuartoRepository;
import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListTipoQuarto;
import br.hoteleveris.app.response.TipoQuartoResponse;
import br.hoteleveris.app.service.TipoQuartoService;

@Service
public class TipoQuartoServiceImp implements TipoQuartoService {
	@Autowired
	private TipoQuartoRepository repository;

	public BaseResponse inserir(TipoQuartoRequest request) {
		TipoQuarto tipoQuarto = new TipoQuarto();

		if (request.getDescricao() == "" || request.getDescricao() == null)
			return new BaseResponse(400, "Erro usuario digite descricao. ");

		if (request.getValor() <= 0) {
			return new BaseResponse(400, "Erro usuario digite valor. ");
		}
		tipoQuarto.setDescricao(request.getDescricao());
		tipoQuarto.setValor(request.getValor());

		repository.save(tipoQuarto);

		return new BaseResponse(201, "Tipo do quarto salvo.");
	}

	public TipoQuartoResponse obter(Long id) {
		Optional<TipoQuarto> tipoQuarto = repository.findById(id);
		TipoQuartoResponse response = new TipoQuartoResponse();

		if (id <= 0) {
			response.message = "id invalido";
			response.statusCode = 404;
			return response;
		}

		if (tipoQuarto.isEmpty()) {
			response.message = "Nao encontrado";
			response.statusCode = 404;
			return response;
		}

		response.setId(tipoQuarto.get().getId());
		response.setDescricao(tipoQuarto.get().getDescricao());
		response.setValor(tipoQuarto.get().getValor());

		response.message = "Obtido com sucesso";
		response.statusCode = 200;
		return response;
	}

	public ListTipoQuarto listar() {
		List<TipoQuarto> lista = repository.findAll();
		ListTipoQuarto response = new ListTipoQuarto();

		response.setTipoQuartos(lista);
		response.statusCode = 200;
		response.message = "Tipo de quartos obtidos com sucesso.";
		return response;
	}

}
