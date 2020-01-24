package org.andrey.springjpa.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class GenError {

	private HttpStatus status;
	private String message;
	private String debugMessage;
	private List<FieldError> fieldValidationErrors;
	
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
	
	public void addValidationErrors(List<FieldError> validationErrors) {
		this.fieldValidationErrors.addAll(validationErrors);
	}
}
