package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	// hozzunk l�tre egy kiv�telkezel�t a CustomerNotFoundException-re
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException cnfe){
		
		// hozzuk l�tre a CustomerErrorResponese-t
		CustomerErrorResponse error = new CustomerErrorResponse(
											HttpStatus.NOT_FOUND.value(),
											cnfe.getMessage(),
											System.currentTimeMillis()
										);
		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	//hozzunk l�tre egy kiv�telkezel�t b�rmilyen m�s hib�ra
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception ex){
		
		// hozzuk l�tre a CustomerErrorResponese-t
		CustomerErrorResponse error = new CustomerErrorResponse(
											HttpStatus.BAD_REQUEST.value(),
											ex.getMessage(),
											System.currentTimeMillis()
										);
		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
