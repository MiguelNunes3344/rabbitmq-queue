package com.br.stock.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
@ControllerAdvice
public class ExceptionHandlerResource {
	
	@ExceptionHandler(ValidateException.class)
    public ResponseEntity<DefaultError> handleException(ValidateException e, HttpServletRequest request) {
		
		DefaultError defaultError = new DefaultError(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),e.getMessage(), request.getRequestURI());
        return ResponseEntity.badRequest().body(defaultError);
    }
}
