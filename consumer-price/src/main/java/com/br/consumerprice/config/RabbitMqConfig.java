package com.br.consumerprice.config;

import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.consumerprice.exception.ErrorHandlerTreatment;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMqConfig {
	
	@Bean
	public ObjectMapper instanciateObjectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public DefaultClassMapper instanciateDefaultClassMapper() {
		return new DefaultClassMapper();
	}
	@Bean
	public Jackson2JsonMessageConverter instanciateJackson() {
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public DirectRabbitListenerContainerFactory instanciateRabbitListener() {
		return new DirectRabbitListenerContainerFactory();
	}

	@Bean
    public MessageConverter jsonToMapMessageConverter(DefaultClassMapper defaultClassMapper,Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        defaultClassMapper.setTrustedPackages("com.br.stock.dto","com.br.consumerprice"); // trusted packages
        jackson2JsonMessageConverter.setClassMapper(defaultClassMapper);
        return jackson2JsonMessageConverter;
    }
	@Bean
    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory, DirectRabbitListenerContainerFactory factory) {
        factory.setConnectionFactory(connectionFactory);
       // Utilizando implementação da ErrorHandler
        factory.setErrorHandler(new ErrorHandlerTreatment());
        return factory;
    }
	
	
}