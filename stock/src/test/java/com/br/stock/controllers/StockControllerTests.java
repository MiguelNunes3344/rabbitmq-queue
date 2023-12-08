package com.br.stock.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.br.stock.controller.StockController;
import com.br.stock.dto.Stock;
import com.br.stock.services.RabbitMqService;

@SpringBootTest
public class StockControllerTests {

	@InjectMocks
	private StockController stockController;
	
	@Mock
	private RabbitMqService rabbitMqService;
	
	@Test
	void successUpdateStock() {
		Stock stock = new Stock("15", 10);
		doNothing().when(rabbitMqService).sendMessage("PRICE", new Object());
		
		ResponseEntity<Stock> response = stockController.updateStock(stock);
		
		assertEquals(Stock.class,response.getBody().getClass());
		assertEquals(stock.getQuantity(),response.getBody().getQuantity());
		assertEquals(stock.getProductCode(),response.getBody().getProductCode());
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
}
