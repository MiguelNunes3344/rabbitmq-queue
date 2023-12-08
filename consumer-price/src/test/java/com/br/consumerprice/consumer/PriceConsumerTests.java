package com.br.consumerprice.consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.consumerprice.models.PriceModel;
import com.br.consumerprice.services.PriceService;
import com.br.stock.dto.PriceDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
public class PriceConsumerTests {

	@InjectMocks
	private PriceConsumer priceConsumer;
	
	@Mock
	private ObjectMapper objectMapper;
	
	@Mock
	private PriceService priceService;
	
	
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	void successSendingTheObjectToSave() throws IOException {
		PriceModel priceModel = Mockito.mock(PriceModel.class);
		
		byte[] b = new byte[5];
		Message msg = Mockito.mock(Message.class);;
		
		
		when(msg.getBody()).thenReturn(b);
		//methods should be equals
		when(objectMapper.readValue(msg.getBody(), PriceModel.class)).thenReturn(priceModel);
		
		
		when(priceService.savePrice(priceModel)).thenReturn(priceModel);
		priceConsumer.consumer(msg);
		
		verify(priceService,times(1)).savePrice(any(PriceModel.class));
		
	}
}
