package com.br.consumerstock.consumer;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.consumerstock.models.StockModel;
import com.br.consumerstock.services.StockService;
import com.br.stock.dto.Stock;
import com.br.stock.enums.RabbitMqQueueName;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StockConsumer {

	@Autowired
	private ObjectMapper objectMapper;
    @Autowired
    private StockService stockService;
    
	@RabbitListener(queues = RabbitMqQueueName.STOCK)
	public void consumer(Message message) throws IOException {
		StockModel  stockModel = objectMapper.readValue(message.getBody(), StockModel.class);
		stockService.save(stockModel);
		System.out.println("Object saved "+ stockModel);
		
	}
}
