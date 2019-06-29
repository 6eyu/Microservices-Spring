package com.learn.account.controller;


import java.util.Date;


import javax.validation.ValidationException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.accounttest.error.AccountUserException;
import com.accounttest.error.ErrorMessage;
import com.accounttest.model.ResponseWrapper;



@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Class<?>> handleException(Exception ex, WebRequest req) {
		
		String errorMessageDescription = ex.getLocalizedMessage();

		if(errorMessageDescription == null) errorMessageDescription = ex.toString();

		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		
		return new ResponseEntity<>(ex.getClass(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {AccountUserException.class})
	public ResponseEntity handleException(AccountUserException ex, WebRequest req) {
		
		ResponseWrapper wrapper = new ResponseWrapper(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(wrapper, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {ValidationException.class})
	public ResponseEntity handleException(ValidationException ex, WebRequest req) {
		
		ResponseWrapper wrapper = new ResponseWrapper(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(wrapper, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {MethodArgumentNotValidException.class})
	public ResponseEntity handleException(MethodArgumentNotValidException ex, WebRequest req) {
		
		ResponseWrapper wrapper = new ResponseWrapper(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(wrapper, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(value= {DataAccessException.class})
	public ResponseEntity handleException(DataAccessException ex, WebRequest req) {
		
		ResponseWrapper wrapper = new ResponseWrapper(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(wrapper, HttpStatus.BAD_REQUEST);
	}
	
}
