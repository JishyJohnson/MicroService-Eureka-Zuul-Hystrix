package com.cart.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cart.model.ErrorMessage;

@ControllerAdvice
public class ShoppingCartException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorMessage> customException(RuntimeException ex) {

		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),"Please check the input");
		return new ResponseEntity<ErrorMessage>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);

	}

}
