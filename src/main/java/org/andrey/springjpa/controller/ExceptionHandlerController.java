package org.andrey.springjpa.controller;

import org.andrey.springjpa.exceptions.EntityNotFoundException;
import org.andrey.springjpa.exceptions.GenError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
																HttpHeaders headers,
																HttpStatus status,
																WebRequest request){
		GenError error = new GenError(status, "Malformed JSON request", ex.getMessage());
		
		return new ResponseEntity<>(error, status);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																HttpHeaders headers,
																HttpStatus status,
																WebRequest request){
		GenError error = new GenError(status, "method arg not valid", ex.getMessage());
		error.addValidationErrors(ex.getBindingResult().getFieldErrors());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){
		GenError error = new GenError(HttpStatus.NOT_FOUND, "entity not found", ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
																WebRequest request){
		String message = String.format("argument '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		GenError error = new GenError(HttpStatus.BAD_REQUEST, message, ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
																HttpHeaders headers,
																HttpStatus status,
																WebRequest request){
		GenError error = new GenError(status, "no handler for that request", ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		GenError error = new GenError(HttpStatus.INTERNAL_SERVER_ERROR, "exception", ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
