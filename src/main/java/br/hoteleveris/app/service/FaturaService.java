package br.hoteleveris.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.repository.OcupacaoRepository;
import br.hoteleveris.app.request.TransferenciaRequest;
import br.hoteleveris.app.response.BaseResponse;

@Service
public class FaturaService {
	@Autowired
	private OcupacaoRepository ocupacaoRepository;

	private String hashContaHotel = "fc9b54e7-7d1b-4876-839e-983f73529d1f";

	public void inserir() {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8081/operacao/transferencia";

		List<Ocupacao> lista = ocupacaoRepository.findBySituacao("N");

		for (Ocupacao ocupacao : lista) {

			double valor = ocupacao.getQuartoId().getTipoQuarto().getValor() * ocupacao.getQtdDiarias();
			System.out.println(valor);

			TransferenciaRequest trasferencia = new TransferenciaRequest();
			trasferencia.setHashDestino(hashContaHotel);
			trasferencia.setHashOrigem(ocupacao.getClienteId().getHashConta());
			trasferencia.setValor(valor);

			BaseResponse response = restTemplate.postForObject(uri, trasferencia, BaseResponse.class);
			System.out.println(response);

			ocupacao.setSituacao("P");
			ocupacaoRepository.save(ocupacao);
		}
	}
}
