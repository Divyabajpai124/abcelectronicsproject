package com.abcelectronicsmartservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientExceptionAdvice {

	@ExceptionHandler(InvalidClientIdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidClientIdException(InvalidClientIdException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(InvalidComplaintIdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidComplaintIdException(InvalidComplaintIdException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(InvalidEngineerIdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidEngineerIdException(InvalidEngineerIdException ex) {
		return ex.getMessage();
	}
	
	
	@ExceptionHandler(InvalidStatusException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidStatusException(InvalidStatusException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidCredentialsException(InvalidCredentialsException ex) {
		return ex.getMessage();
	}
}
