package com.br.stock.connection;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.br.stock.enums.RabbitMqQueueName;

import jakarta.annotation.PostConstruct;

@Component
public class RabbitMqConnection {
	private static final String EXCHANGE_NAME = "amq.direct";
	
	private final AmqpAdmin amqpAdmin;
	
	public RabbitMqConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}
	
	
	@PostConstruct
	private void initializer() {
		Queue stockQueue = this.createQueue(RabbitMqQueueName.STOCK);
		Queue priceQueue = this.createQueue(RabbitMqQueueName.PRICE);
		
		DirectExchange directExchange = this.declareExchange();
		
		Binding relationStock = this.createRelation(stockQueue, directExchange); 
		Binding relationPrice = this.createRelation(priceQueue, directExchange); 
		
		amqpAdmin.declareQueue(priceQueue);
		amqpAdmin.declareQueue(stockQueue);
		
		amqpAdmin.declareExchange(directExchange);
		
		amqpAdmin.declareBinding(relationStock);
		amqpAdmin.declareBinding(relationPrice);
		
	}
	
	
	private Binding createRelation(Queue queue, DirectExchange directExchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
	}
	
	private Queue createQueue(String name) {
		return new Queue(name,true,false,false,null);
	}
	private DirectExchange declareExchange() {
		return new DirectExchange(EXCHANGE_NAME, true, false, null);
	}
}
