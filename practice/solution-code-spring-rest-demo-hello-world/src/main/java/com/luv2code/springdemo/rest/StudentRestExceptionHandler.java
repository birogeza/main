package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

	//add exception handling code here
	// Adjunk hozz? exception-handlert az @ExceptionHandler haszn?lat?val
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
			
			//create a StudentErrorResponse
			StudentErrorResponse error = new StudentErrorResponse();
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			//return ResponseEntity
			
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		// hozzunk l?tre egy m?sik ExceptionHandlert, ami minden hib?t elkap:
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
			
			//create a StudentErrorResponse
			StudentErrorResponse error = new StudentErrorResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			//return ResponseEntity
			
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
}
