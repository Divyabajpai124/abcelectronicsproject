package com.abcelectronicsmartservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ProductExceptionAdvice {

	@ExceptionHandler(InvalidProductIdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidProductIdException(InvalidProductIdException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(InvalidCategoryNameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidCategoryNameException(InvalidCategoryNameException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(InvalidModelNumberException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidModelNumberException(InvalidModelNumberException ex) {
		return ex.getMessage();
	}
}
