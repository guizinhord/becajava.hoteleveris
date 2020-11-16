package br.hoteleveris.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.request.ClienteRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ClienteResponse;

@SpringBootTest
public class ClienteTest {

	@Autowired
	private ClienteService service;

	@Test
	public void criar() {
		ClienteRequest request = new ClienteRequest();
		request.setCpf("5595");
		request.setHashConta("&#@*!(");
		request.setNome("EOWQP");
		
		BaseResponse response = service.inserir(request);
		Assertions.assertEquals(201, response.getStatusCode());
		Assertions.assertEquals("Cliente Salvo.", response.getMessage());
	}
	

	@Test
	public void obter() {
		ClienteResponse response = service.obter(1L);
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Obtidos", response.getMessage());
	}
	
	@Test
	public void IdInvalido() {
		ClienteResponse response = service.obter(17478412L);
		Assertions.assertEquals(404, response.getStatusCode());
		Assertions.assertEquals("Id invalido.", response.getMessage());
	}
	
	@Test
	public void IdNegativo() {
		ClienteResponse response = service.obter(-1L);
		Assertions.assertEquals(404, response.getStatusCode());
		Assertions.assertEquals("Id invalido.", response.getMessage());
	}
	
}