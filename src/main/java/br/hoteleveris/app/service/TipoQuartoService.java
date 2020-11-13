package br.hoteleveris.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.repository.TipoQuartoRepository;
import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListTipoQuarto;

@Service
public class TipoQuartoService {
	@Autowired
	private TipoQuartoRepository repository;

	public BaseResponse inserir(TipoQuartoRequest request) {
		TipoQuarto tipoQuarto = new TipoQuarto();
		BaseResponse base = new BaseResponse();
		base.statusCode = 400;

		if (request.getDescricao() == "") {
			base.message = "Preencha o campo descricão.";
			return base;
		}
		if (request.getValor() <= 0) {
			base.message = "Preencha o campo valor.";
			return base;
		}
		tipoQuarto.setDescricao(request.getDescricao());
		tipoQuarto.setValor(request.getValor());

		repository.save(tipoQuarto);
		base.message = "Tipo do quarto salvo.";
		base.statusCode = 200;
		return base;
	}

	public TipoQuarto obter(Long id) {
		Optional<TipoQuarto> tipoQuarto = repository.findById(id);
		TipoQuarto response = new TipoQuarto();

		if (tipoQuarto.isEmpty()) {
			response.message = "Nao encontrado";
			response.statusCode = 404;
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
		response.message = " Tipo de quartos obtidos com sucesso.";
		return response;
	}

}
