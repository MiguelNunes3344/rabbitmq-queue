package com.br.consumerstock.consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Message;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.br.consumerstock.models.StockModel;
import com.br.consumerstock.services.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@ActiveProfiles("test")
public class StockConsumerTests {

	@InjectMocks
	private StockConsumer stockConsumer;
	
	@Mock
	private ObjectMapper objectMapper;
	
	@Mock
	private StockService stockService;
	
	
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	void successSendingObjectToSave() throws IOException {
		StockModel stockModel = Mockito.mock(StockModel.class);
		byte[] b = new byte[5];
		Message msg = Mockito.mock(Message.class);;
		
		
		when(msg.getBody()).thenReturn(b);
		when(objectMapper.readValue(msg.getBody(), StockModel.class)).thenReturn(stockModel);
		when(stockService.save(stockModel)).thenReturn(stockModel);
		
		stockConsumer.consumer(msg);
		
		verify(stockService,times(1)).save(any(StockModel.class));
		
	}
}
