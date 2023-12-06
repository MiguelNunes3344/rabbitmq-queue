package com.br.consumerstock.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.br.stock.dto.Stock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StockConsumer {

	private final static String NAME_QUEUE = "STOCK";
	
	
	private final ObjectMapper objectMapper;

    public StockConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
	@RabbitListener(queues = NAME_QUEUE)
	public void consumer(Message message) throws JsonMappingException, JsonProcessingException {
		String messageBody = new String(message.getBody());
		Stock  yourObject = objectMapper.readValue(messageBody, Stock.class);
		System.out.println("Event consumed "+yourObject.getProductCode()+" port 8082");
		
	}
}