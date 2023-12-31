package com.br.stock.services;


import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.br.stock.dto.PriceDto;
import com.br.stock.dto.Stock;
import com.br.stock.exceptions.ValidateException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class RabbitMqService {

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String queueName, Object message)  {
		validateObject(message);
		try {
			this.rabbitTemplate.convertAndSend(queueName,objectMapper.writeValueAsString(message));
		} catch (JsonProcessingException | AmqpException e) {
			e.printStackTrace();
		}
	}
	
	private void validateObject(Object message) {
		if (message  instanceof PriceDto) {
			PriceDto priceDto = (PriceDto) message;
			if (priceDto.getProductCode() <= 0) {
				throw new ValidateException("Product code must be greatest than 0", HttpStatus.BAD_REQUEST);
			} else if (priceDto.getPrice() <= 0 ) {
				throw new ValidateException("Price must be greatest than 0",HttpStatus.BAD_REQUEST);
			}
		} else {
			Stock stock = (Stock) message;
			if (stock.getProductCode()==null) {
				throw new ValidateException("Product code must be informed",HttpStatus.BAD_REQUEST);
			} else if (stock.getQuantity() <= 0 ) {
				throw new ValidateException("Quantity must be greatest than 0",HttpStatus.BAD_REQUEST);
			}
		}
		
	}
	
}
