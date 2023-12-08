package com.br.consumerstock.consumer.config;

import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMqConfig {
	
		@Bean
	    public MessageConverter jsonToMapMessageConverter() {
	        DefaultClassMapper defaultClassMapper = new DefaultClassMapper();
	        defaultClassMapper.setTrustedPackages("com.br.stock.dto"); // trusted packages
	        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
	        jackson2JsonMessageConverter.setClassMapper(defaultClassMapper);
	        return jackson2JsonMessageConverter;
	    }
	 	@Bean
	    public ObjectMapper objectMapper() {
	        return new ObjectMapper();
	    }
	 	/*
	 	@Bean
	    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
	        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();

	        factory.setConnectionFactory(connectionFactory);
	        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);

	        factory.setPrefetchCount(4);
	        //factory.setGlobalQos(true);

	       // Utilizando implementação da ErrorHandler
	       // factory.setErrorHandler(new TratamentoErroHandler());

	        // Utilizando implementação da FatalStrategy
	        factory.setErrorHandler(errorHandler());

	        return factory;
	    }
	    */
	 	
	 	
	 	
}
