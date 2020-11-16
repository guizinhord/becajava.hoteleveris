package br.hoteleveris.app.response;

public class BaseResponse {
	public int statusCode;
	public String message;

	public BaseResponse(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;

	}

	public BaseResponse() {
	}

}
