package com.br.consumerprice.consumer;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.br.stock.dto.PriceDto;
import com.br.stock.enums.RabbitMqQueueName;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PriceConsumer {

	private final ObjectMapper objectMapper;
	
	
	
	public PriceConsumer(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}



	@RabbitListener(queues = RabbitMqQueueName.PRICE)
	public void consumer(Message message) throws StreamReadException, DatabindException, IOException {
		PriceDto priceDto = objectMapper.readValue(message.getBody(), PriceDto.class);
		System.out.println(priceDto.getPrice());
		System.out.println(priceDto.getProductCode());
		
	}

}