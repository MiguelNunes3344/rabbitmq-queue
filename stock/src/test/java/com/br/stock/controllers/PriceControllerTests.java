package com.br.stock.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.br.stock.controller.PriceController;
import com.br.stock.dto.PriceDto;
import com.br.stock.services.RabbitMqService;

@SpringBootTest
public class PriceControllerTests {

	@Mock
	private RabbitMqService rabbitMqService;
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private PriceController priceController;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void successUpdatePrice() {
		PriceDto priceDtoRequest = new PriceDto(15,10);
		
		ResponseEntity<PriceDto> mockResponseEntity = ResponseEntity.ok(priceDtoRequest);
		doNothing().when(rabbitMqService).sendMessage(any(String.class), any(Object.class));
		
		ResponseEntity<PriceDto> response = priceController.updatePrice(priceDtoRequest);
		
		assertEquals(PriceDto.class,response.getBody().getClass());
		assertEquals(priceDtoRequest.getPrice(),response.getBody().getPrice());
		assertEquals(priceDtoRequest.getProductCode(),response.getBody().getProductCode());
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
		
	
}
