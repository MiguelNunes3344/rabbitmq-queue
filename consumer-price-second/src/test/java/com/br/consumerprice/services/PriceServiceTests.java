package com.br.consumerprice.services;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.br.consumerprice.consumer.PriceConsumer;
import com.br.consumerprice.models.PriceModel;
import com.br.consumerprice.repository.PriceRepository;

import jakarta.persistence.Column;

@SpringBootTest
@ActiveProfiles("test")
public class PriceServiceTests {

	@InjectMocks
	PriceService priceService;
	
	@Mock
	PriceRepository priceRepository;
	
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		
	}
	@Test
	void verifyPriceRepository() {
		
		PriceModel priceModel = new PriceModel(UUID.fromString("fb55c4e3-c1af-492a-84a2-fe971c14edde"), 10, 15.0);
		
		when(priceRepository.save(any(PriceModel.class))).thenReturn(priceModel);
		
		PriceModel priceReturned = priceService.savePrice(priceModel);
		
		assertEquals(PriceModel.class,priceReturned.getClass());
		assertEquals(priceModel.getProductCode(),priceReturned.getProductCode());
		assertEquals(priceModel.getPrice(),priceReturned.getPrice());
		verify(priceRepository).save(any(PriceModel.class));
	}
	
}
