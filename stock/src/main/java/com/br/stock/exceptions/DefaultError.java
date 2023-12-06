package com.br.stock.exceptions;

import java.time.LocalDateTime;

public class DefaultError {
	
	private LocalDateTime timestamp;
	private int status;
	private String message;
	private String path;
	
	public DefaultError() {
		
	}

	
	
	public DefaultError(LocalDateTime timestamp, int status, String messageError, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.message = messageError;
		this.path = path;
	}



	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessageError() {
		return message;
	}

	public void setMessageError(String messageError) {
		this.message = messageError;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
