package com.abcelectronicsmartservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AdminExceptionAdvice {

	@ExceptionHandler(PleaseCheckDetailsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String pleaseCheckDetailsException(PleaseCheckDetailsException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String userAlreadyExistException(UserAlreadyExistException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(InvalidEngineerIdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidEngineerIdException(InvalidEngineerIdException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(InvalidDomainException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidDomainException(InvalidDomainException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(InvalidComplaintIdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidComplaintIdException(InvalidComplaintIdException ex) {
		return ex.getMessage();
	}
}
