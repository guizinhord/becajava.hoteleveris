package br.hoteleveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListTipoQuarto;
import br.hoteleveris.app.response.TipoQuartoResponse;
import br.hoteleveris.app.service.TipoQuartoService;
import io.swagger.models.Response;

@RestController
@RequestMapping("/tipoDeQuartos")
public class TipoQuartoController {
	@Autowired
	private TipoQuartoService service;

	@PostMapping
	public ResponseEntity inserir(@RequestBody TipoQuartoRequest request) {
		try {
			BaseResponse response = service.inserir(request);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(new BaseResponse(500, "Erro nao esperado").statusCode)
					.body(new BaseResponse(500, "Erro nao esperado"));
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity obter(@PathVariable Long id) {
		try {
			TipoQuarto response = service.obter(id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(500, "Erro nao esperado"));
		}
	}

	@GetMapping
	public ResponseEntity listar() {
		try {
			ListTipoQuarto contas = service.listar();
			return ResponseEntity.status(contas.statusCode).body(contas);
		} catch (Exception e) {
			return ResponseEntity.status(new BaseResponse(500, "Erro nao esperado").statusCode)
					.body(new BaseResponse(500, "Erro nao esperado"));
		}
	}
}
