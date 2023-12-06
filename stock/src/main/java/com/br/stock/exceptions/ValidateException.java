package com.br.stock.exceptions;

import org.springframework.http.HttpStatusCode;

public class ValidateException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatusCode status;
	public ValidateException(String msg, HttpStatusCode status) {

		super(msg);
		this.status = status;
	}
	public int getStatus() {
		return status.value();
	}
}
