package br.hoteleveris.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.repository.OcupacaoRepository;
import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListOcupacao;

@Service
public class OcupacaoService {
	@Autowired
	private OcupacaoRepository repository;

	public BaseResponse inserir(OcupacaoRequest request) {
		BaseResponse base = new BaseResponse();

		base.statusCode = 404;

		if (request.getClienteId() == null) {
			base.message = "Informe id do cliente.";
			return base;
		}
		if (request.getQuartoId() == null) {
			base.message = "Informe id do quarto.";
			return base;
		}

		if (request.getQtdDiarias() <= 0) {
			base.message = "Informe quantidade de diarias.";
			return base;
		}

		if (request.getSituacao() == "") {
			base.message = "Informe situacao.";
			return base;
		}

		request.setSituacao("N");

		Cliente cliente = new Cliente();
		cliente.setId(request.getClienteId());

		Quarto quarto = new Quarto();
		quarto.setId(request.getQuartoId());

		Ocupacao ocupacao = new Ocupacao();
		ocupacao.setClienteId(cliente);
		ocupacao.setQtdDiarias(request.getQtdDiarias());
		ocupacao.setQuartoId(quarto);

		repository.save(ocupacao);
		base.message = "Ocupacao inserida com sucesso";
		base.statusCode = 201;
		return base;
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
