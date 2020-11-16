package br.hoteleveris.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.request.QuartoPatchRequest;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.QuartoResponse;

@SpringBootTest
public class QuartoTest {
	@Autowired
	private QuartoService service;

	@Test
	public void criar() {
		QuartoRequest request = new QuartoRequest();
		request.setAndar("4");

		List<ComodidadeRequest> teste = new ArrayList<ComodidadeRequest>();
		ComodidadeRequest comodidade = new ComodidadeRequest();
		comodidade.setId(1L);
		teste.add(comodidade);

		request.setComodidade(teste);
		request.setTipoQuartoId(1L);
		int noQuarto = this.getRandomNumberUsingInts(1, 1000);
		request.setNumQuarto(noQuarto);
		request.setSituacao("Ativo");

		BaseResponse response = service.inserir(request);

		Assertions.assertEquals(201, response.getStatusCode());
		Assertions.assertEquals("Quarto Salvo.", response.getMessage());

	}

	@Test
	public void obter() {
		Long id = 13L;
		QuartoResponse response = service.obter(id);

		Assertions.assertEquals(200, response.getStatusCode());

	}

	public int getRandomNumberUsingInts(int min, int max) {
		Random random = new Random();
		return random.ints(min, max).findFirst().getAsInt();

	}
	
	@Test
	public void atualizarSituacao() {
		QuartoPatchRequest request = new QuartoPatchRequest();
		request.setSituacao("I");
		
		Long id = 13L;	
		BaseResponse response = service.atualizar(id, request);
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Atualizado com sucesso ", response.getMessage());

	}
}
