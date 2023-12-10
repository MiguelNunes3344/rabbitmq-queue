package com.br.consumerstock.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.br.consumerstock.models.StockModel;
import com.br.consumerstock.repository.StockRepository;


@SpringBootTest
@ActiveProfiles("test")
public class StockServiceTests {

	@InjectMocks
	StockService stockService;
	
	@Mock
	StockRepository stockRepository;
	
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		
	}
	@Test
	void verifyPriceRepository() {
		
		StockModel stockModel = new StockModel(UUID.fromString("fb55c4e3-c1af-492a-84a2-fe971c14edde"), "10", 15);
		
		when(stockRepository.save(any(StockModel.class))).thenReturn(stockModel);
		
		StockModel priceReturned = stockService.save(stockModel);
		
		assertEquals(StockModel.class,priceReturned.getClass());
		assertEquals(stockModel.getProductCode(),priceReturned.getProductCode());
		assertEquals(stockModel.getQuantity(),priceReturned.getQuantity());
		verify(stockRepository).save(any(StockModel.class));
	}
	
}
