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

	public BaseResponse inserir() {

		BaseResponse response = new BaseResponse();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/operacao/transferencia";
		String hashContaHotel = "fc9b54e7-7d1b-4876-839e-983f73529d1f";

		List<Ocupacao> lista = ocupacaoRepository.findBySituacao("N");

		if (lista.isEmpty()) {
			response.message = "Não Há nenhuma Ocupação em debito.";
			response.statusCode = 400;
			return response;

		}

		for (Ocupacao ocupacao : lista) {
			double valor = ocupacao.getQuartoId().getTipoQuarto().getValor() * ocupacao.getQtdDiarias();

			TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
			transferenciaRequest.setHashDestino(hashContaHotel);
			transferenciaRequest.setHashOrigem(ocupacao.getClienteId().getHashConta());
			transferenciaRequest.setValor(valor);

			restTemplate.postForObject(url, transferenciaRequest, BaseResponse.class);

			ocupacao.setSituacao("P");
			ocupacaoRepository.save(ocupacao);

		}
		response.statusCode = 200;
		response.message = "Transferencia completa";

		return response;

	}
}
