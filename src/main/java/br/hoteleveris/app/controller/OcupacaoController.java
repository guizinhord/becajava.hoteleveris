package br.hoteleveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListOcupacao;
import br.hoteleveris.app.response.ListTipoQuarto;
import br.hoteleveris.app.service.OcupacaoService;

@RestController
@RequestMapping("/ocupacao")
public class OcupacaoController {
	@Autowired
	private OcupacaoService service;

	@PostMapping
	public ResponseEntity inserir(@RequestBody OcupacaoRequest request) {
		try {
			BaseResponse response = service.inserir(request);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new BaseResponse(500, "Erro nao esperado"));
		}
	}

	@GetMapping
	public ResponseEntity listar() {
		try {
			ListOcupacao ocupacoes = service.listar();
			return ResponseEntity.status(ocupacoes.statusCode).body(ocupacoes);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new BaseResponse(500, "Erro nao esperado"));
		}
	}
}
