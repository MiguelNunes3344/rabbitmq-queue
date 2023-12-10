package com.br.consumerprice.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.br.consumerprice.exception.ErrorHandlerTreatment;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
public class RabbitMqConfigTests {

	@InjectMocks
	private RabbitMqConfig rabbitMqConfig;
	
	@Mock
	private ObjectMapper objectMapper;
	
	@Mock
	private DefaultClassMapper defaultClassMapper;
	
	@Mock
	private Jackson2JsonMessageConverter jackson2JsonMessageConverter;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void sucessInstanciateObjectMapper() {
		
		ObjectMapper objectReturned = rabbitMqConfig.instanciateObjectMapper();
		assertEquals(ObjectMapper.class,objectReturned.getClass());
	}
	
	@Test
	void sucessInstanciateDefaultClassMapper() {
		
		DefaultClassMapper objectReturned = rabbitMqConfig.instanciateDefaultClassMapper();
		assertEquals(DefaultClassMapper.class,objectReturned.getClass());
		
	}
	
	@Test
	void sucessInstanciateJackson() {
		
		Jackson2JsonMessageConverter objectReturned= rabbitMqConfig.instanciateJackson();
		assertEquals(Jackson2JsonMessageConverter.class,objectReturned.getClass());	
	}
	
	@Test
	void sucessInstanciateRabbitListener() {
		
		DirectRabbitListenerContainerFactory objectReturned = rabbitMqConfig.instanciateRabbitListener();
		assertEquals(DirectRabbitListenerContainerFactory.class,objectReturned.getClass());
		
	}
	
	@Test
	void successConfigMessageConverter() {
		
		var defaultClassMapper = mock(DefaultClassMapper.class);
		var jackson2JsonMessageConverter = mock(Jackson2JsonMessageConverter.class);
		
		doNothing().when(defaultClassMapper).setTrustedPackages("com.br.stock.dto","com.br.consumerprice");
		doNothing().when(jackson2JsonMessageConverter).setClassMapper(defaultClassMapper);
		
		MessageConverter msg = rabbitMqConfig.jsonToMapMessageConverter(defaultClassMapper,jackson2JsonMessageConverter);
		
		verify(defaultClassMapper,times(1)).setTrustedPackages("com.br.stock.dto","com.br.consumerprice");
		verify(jackson2JsonMessageConverter,times(1)).setClassMapper(defaultClassMapper);
		assertEquals(Jackson2JsonMessageConverter.class,msg.getClass());
		
	}
	
	@Test
	void configRabbitListener() {
		
		var connectionFactory = mock(ConnectionFactory.class);
		var factory = mock(DirectRabbitListenerContainerFactory.class);
		var response=rabbitMqConfig.rabbitListenerContainerFactory(connectionFactory, factory);
		
		verify(factory,times(1)).setConnectionFactory(connectionFactory);
		verify(factory,times(1)).setErrorHandler(any(ErrorHandlerTreatment.class));
		
		assertEquals(DirectRabbitListenerContainerFactory.class,response.getClass());
		
	}
	
}
