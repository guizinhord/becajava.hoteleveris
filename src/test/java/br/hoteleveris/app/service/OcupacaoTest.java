package br.hoteleveris.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListOcupacao;

@SpringBootTest
public class OcupacaoTest {
	@Autowired
	private OcupacaoService service;

	@Test
	public void criar() {

		OcupacaoRequest request = new OcupacaoRequest();

		request.setClienteId(3L);
		request.setQuartoId(13L);
		request.setQtdDiarias(4);
		request.setSituacao("A");

		BaseResponse response = service.inserir(request);
		Assertions.assertEquals(201, response.getStatusCode());
		Assertions.assertEquals("Ocupacao inserida com sucesso", response.getMessage());
	}

	@Test
	public void SemIdCliente() {

		OcupacaoRequest request = new OcupacaoRequest();

		request.setClienteId(null);
		request.getQuartoId();
		request.getQtdDiarias();
		request.setSituacao("A");

		BaseResponse response = service.inserir(request);
		Assertions.assertEquals(404, response.getStatusCode());
		Assertions.assertEquals("Preencha id do Cliente.", response.getMessage());
	}
	
	@Test
	public void listar() {
		
		ListOcupacao response = service.listar();
		
		assertThat(!response.getOcupacoes().isEmpty());
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Lista Obtida", response.getMessage());
		
	}
	

}
