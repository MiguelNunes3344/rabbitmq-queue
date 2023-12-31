package com.br.stock.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.stock.dto.PriceDto;
import com.br.stock.enums.RabbitMqQueueName;
import com.br.stock.services.RabbitMqService;

@RestController
@RequestMapping("price")
public class PriceController {

	@Autowired
	private RabbitMqService rabbitMqService;
	
	@PutMapping
	public ResponseEntity<PriceDto> updatePrice(@RequestBody PriceDto priceDto) {
		rabbitMqService.sendMessage(RabbitMqQueueName.PRICE, priceDto);
		return ResponseEntity.status(HttpStatus.OK).body(priceDto);
	}
	
	
}
