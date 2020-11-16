package br.hoteleveris.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.repository.ClienteRepository;
import br.hoteleveris.app.request.ClienteRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ClienteResponse;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	public BaseResponse inserir(ClienteRequest request) {
		if(request.getNome() == "" || request.getNome() == null)
			return new BaseResponse(404, "Preencha o campo nome");
		if(request.getCpf() == "" || request.getCpf() == null)
			return new BaseResponse(404, "Preencha o campo cpf");
		if(request.getHashConta() == "" || request.getHashConta() == null)
			return new BaseResponse(404, "Preencha o campo hash");
		
		Cliente cliente = new Cliente(
				request.getNome(),
				request.getCpf(),
				request.getHashConta()
				);
		repository.save(cliente);
		return new BaseResponse(201,"Cliente Salvo.");
	}
	
	public ClienteResponse obter(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		ClienteResponse response = new ClienteResponse();
		
		if (!cliente.isPresent()) {
			response.message = "Id invalido.";
			response.statusCode = 404;
			return response;
		}
		response.setId(cliente.get().getId());
		response.setNome(cliente.get().getNome());
		response.setCpf(cliente.get().getCpf());
		
		response.statusCode = 200;
		response.message = "Obtidos";
		return response;
	}

	
}
