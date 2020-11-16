package br.hoteleveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.service.FaturaService;

@RestController
@RequestMapping("/fatura")
public class FaturaController {
	@Autowired
	private FaturaService service;
	
	@PostMapping
	public ResponseEntity<BaseResponse> inserir() {
		try {
			 BaseResponse response = service.inserir();
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(500, "Erro server."));
		}
	}
}
