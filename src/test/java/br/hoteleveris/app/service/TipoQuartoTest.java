package br.hoteleveris.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListTipoQuarto;
import br.hoteleveris.app.response.TipoQuartoResponse;

@SpringBootTest
public class TipoQuartoTest {

	@Autowired
	private TipoQuartoService service;

	@Test
	void inserir() throws Exception {
		TipoQuartoRequest request = new TipoQuartoRequest();
		if (request.getDescricao() == null || request.getDescricao() == "")
			new BaseResponse(400, "Erro usuario digite descricao.");
		if (request.getValor() <= 0) {
			new BaseResponse(400, "Erro usuario digite valor.");
		}

		request.setDescricao("Simples");
		request.setValor(50);

		BaseResponse response = service.inserir(request);

		Assertions.assertEquals(201, response.getStatusCode());
	}

	@Test
	void inserirSemDescricao() throws Exception {
		TipoQuartoRequest request = new TipoQuartoRequest();
		if (request.getDescricao() == null || request.getDescricao() == "")
			new BaseResponse(400, "Erro usuario digite descricao.");
		if (request.getValor() <= 0) {
			new BaseResponse(400, "Erro usuario digite valor.");
		}

		// request.setDescricao("Simples");
		request.setValor(50);

		BaseResponse response = service.inserir(request);

		Assertions.assertEquals(400, response.getStatusCode());
	}

	@Test
	void inserirSemValor() throws Exception {
		TipoQuartoRequest request = new TipoQuartoRequest();
		if (request.getDescricao() == null || request.getDescricao() == "")
			new BaseResponse(400, "Erro usuario digite descricao.");
		if (request.getValor() <= 0) {
			new BaseResponse(400, "Erro usuario digite valor.");
		}

		request.setDescricao("Simples");
		// request.setValor(50);

		BaseResponse response = service.inserir(request);

		Assertions.assertEquals(400, response.getStatusCode());
	}
	

	@Test
	void obter() {
		TipoQuartoResponse response = service.obter(1L);
		
		Assertions.assertEquals(200, response.statusCode);
		Assertions.assertEquals("Obtido com sucesso", response.message);
	}

	@Test
	void obterTipoQuartonumeroInexistente() {
		TipoQuartoResponse response = service.obter(50L);
		
		Assertions.assertEquals(404, response.statusCode);
		Assertions.assertEquals("Nao encontrado", response.message);
	}
	
	@Test
	void obterTipoQuartoIdInvalido() {
		TipoQuartoResponse response = service.obter(-1L);
		
		Assertions.assertEquals(404, response.statusCode);
		Assertions.assertEquals("id invalido", response.message);
	}

	@Test
	void obterlistageral() {

		ListTipoQuarto response = service.listar();
		
		
		Assertions.assertEquals(200, response.statusCode);
		Assertions.assertEquals("Tipo de quartos obtidos com sucesso.", response.message);

	}

}
