package br.hoteleveris.app.request;

public class ClienteRequest {
	private String nome;
	private String cpf;
	private String hashConta;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getHashConta() {
		return hashConta;
	}

	public void setHashConta(String hashConta) {
		this.hashConta = hashConta;
	}

}
