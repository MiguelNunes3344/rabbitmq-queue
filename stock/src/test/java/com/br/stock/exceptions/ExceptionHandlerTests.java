package com.br.stock.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
public class ExceptionHandlerTests {

	@InjectMocks
	ExceptionHandlerResource exceptionHandlerResource;
	
	@Test
	void successReturnTypeOfException() {
		ValidateException  validation = new ValidateException("error",HttpStatus.BAD_GATEWAY);
		HttpServletRequest httpServletRequest = new MockHttpServletRequest();
		ResponseEntity<DefaultError> response = exceptionHandlerResource.handleException(validation, httpServletRequest);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(DefaultError.class, response.getBody().getClass());
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals("error",response.getBody().getMessageError());
		
		
	}
}
