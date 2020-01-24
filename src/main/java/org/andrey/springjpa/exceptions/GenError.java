package org.andrey.springjpa.exceptions;

import org.springframework.http.HttpStatus;

public class GenError {

	private HttpStatus status;
	private String message;
	private String debugMessage;
	
	public GenError(HttpStatus status, String message, String debugMessage) {
		this.status = status;
		this.message = message;
		this.debugMessage = debugMessage;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDebugMessage() {
		return debugMessage;
	}
}
