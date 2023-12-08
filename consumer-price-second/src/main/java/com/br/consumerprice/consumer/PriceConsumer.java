package com.br.consumerprice.consumer;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.consumerprice.models.PriceModel;
import com.br.consumerprice.services.PriceService;
import com.br.stock.enums.RabbitMqQueueName;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PriceConsumer {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private PriceService priceService;
	
	

	@RabbitListener(queues = RabbitMqQueueName.PRICE)
	public void consumer(Message message) throws StreamReadException, DatabindException, IOException {
		PriceModel priceModel = objectMapper.readValue(message.getBody(), PriceModel.class);
		priceService.savePrice(priceModel);
		System.out.println("Price saved: "+ priceModel);
	}

}
