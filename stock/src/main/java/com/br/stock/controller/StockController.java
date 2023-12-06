package com.br.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.stock.dto.Stock;
import com.br.stock.enums.RabbitMqQueueName;
import com.br.stock.services.RabbitMqService;

@RestController
@RequestMapping("stock")
public class StockController {

	@Autowired
	private RabbitMqService rabbitMqService;
	
	@PutMapping
	public ResponseEntity<?> updateStock(@RequestBody Stock stock) {
		rabbitMqService.sendMessage(RabbitMqQueueName.STOCK, stock);
		return ResponseEntity.status(HttpStatus.OK).body(stock);
	}
	
	
}
