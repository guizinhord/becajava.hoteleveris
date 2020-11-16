package br.hoteleveris.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.model.Comodidade;
import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ComodidadeResponse;

@SpringBootTest
public class ComodidadeTest {

	@Autowired
	private ComodidadeService service;
	
	@Test
	public void criar() {
		ComodidadeRequest request = new ComodidadeRequest();
		request.setNome("Teste");
		
	
		BaseResponse response = service.inserir(request);
		Assertions.assertEquals(201, response.getStatusCode());
		Assertions.assertEquals("Comodidade salva.", response.message);
	}
	
	@Test
	public void obter() {
		Long id = 1L;
		ComodidadeResponse response = service.obter(id);
		Assertions.assertEquals(201, response.getStatusCode());
	}
	@Test
	public void obterIdInexistente() {
		Long id = 100L;
		ComodidadeResponse response = service.obter(id);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Nao encontrado", response.message);
	}
	
}
