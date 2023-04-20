package com.hdfc.midterm.library_management_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(){
		return new ResponseEntity<String>("User Not Found!",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<String> bookNotFoundException(){
		return new ResponseEntity<String>("Book Not Found!",HttpStatus.NOT_FOUND);
	}
}
