package com.br.stock.services;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.br.stock.dto.PriceDto;
import com.br.stock.dto.Stock;
import com.br.stock.enums.RabbitMqQueueName;
import com.br.stock.exceptions.ValidateException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class RabbitMqServiceTests {
	private static final String PRICE = "PRICE";

	@Autowired
    RestTemplate restTemplate;
	
    @InjectMocks
    RabbitMqService rabbitMqService;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void successSendMessage() throws JsonProcessingException {
        PriceDto priceDtoRequest = new PriceDto(15,10);
        
        rabbitMqService.sendMessage(PRICE, priceDtoRequest);
        verify(rabbitTemplate,times(1)).convertAndSend(PRICE,objectMapper.writeValueAsString(priceDtoRequest));
    }
    @Test
    void failSendMessage() throws JsonProcessingException {
        PriceDto priceDtoRequest = new PriceDto(15,10);
        doThrow(new AmqpException("error")).when(rabbitTemplate).convertAndSend(PRICE, objectMapper.writeValueAsString(priceDtoRequest));
        
        rabbitMqService.sendMessage(PRICE, priceDtoRequest);
        
        AmqpException e =assertThrows(AmqpException.class, () -> {
        	rabbitTemplate.convertAndSend(PRICE, objectMapper.writeValueAsString(priceDtoRequest));
        });
       
    }
    


    @Test
    void successPrice() {
        PriceDto priceDtoRequest = new PriceDto(15,10);
        HttpEntity<PriceDto> requestEntity = new HttpEntity<>(priceDtoRequest);
        ResponseEntity<PriceDto> dto = restTemplate.exchange("http://localhost:8080/price", HttpMethod.PUT,requestEntity,PriceDto.class);
        assertEquals(PriceDto.class,dto.getBody().getClass());
    }
    @Test
    @DisplayName("When the product code is 0 then throw a exception")
    void failurePrice() {
    	PriceDto priceDtoRequest = new PriceDto(0,10);
        ValidateException thrown = Assertions.assertThrows(ValidateException.class,() -> {
            rabbitMqService.sendMessage(RabbitMqQueueName.PRICE,priceDtoRequest);
        });
        assertEquals(HttpStatus.BAD_REQUEST.value(),thrown.getStatus());
    	assertEquals("Product code must be greatest than 0", thrown.getMessage());
    }

    @Test
    @DisplayName("When the price is less than 0 then throw a exception")
    void failurePriceWithNullValue() {
        PriceDto priceDtoRequest = new PriceDto(20,0);
        ValidateException thrown = Assertions.assertThrows(ValidateException.class,() -> {
            rabbitMqService.sendMessage(RabbitMqQueueName.PRICE,priceDtoRequest);
        });
        
        assertEquals(HttpStatus.BAD_REQUEST.value(),thrown.getStatus());
        assertEquals("Price must be greatest than 0", thrown.getMessage());
    }

    @Test
    void successStock() {
        Stock stockDto = new Stock("10",10);
        HttpEntity<Stock> requestEntity = new HttpEntity<>(stockDto);
        ResponseEntity<Stock> dto = restTemplate.exchange("http://localhost:8080/stock", HttpMethod.PUT,requestEntity,Stock.class);
        
        assertEquals(HttpStatus.OK,dto.getStatusCode());
        assertEquals(Stock.class,dto.getBody().getClass());
    }

    @Test
    @DisplayName("When the product is null")
    void failureStockWithNullValue() {
        Stock stockDto = new Stock(null,10);
        ValidateException thrown = Assertions.assertThrows(ValidateException.class,() -> {
            rabbitMqService.sendMessage(RabbitMqQueueName.STOCK,stockDto);
        });
        
        assertEquals(HttpStatus.BAD_REQUEST.value(),thrown.getStatus());
        assertEquals("Product code must be informed", thrown.getMessage());
    }

    @Test
    @DisplayName("When the quantity is less than 0 then throw a exception")
    void failureStockQuantity() {
        Stock stockDto = new Stock("15",0);
        ValidateException thrown = Assertions.assertThrows(ValidateException.class,() -> {
            rabbitMqService.sendMessage(RabbitMqQueueName.STOCK,stockDto);
        });
        
        assertEquals(HttpStatus.BAD_REQUEST.value(),thrown.getStatus());
        assertEquals("Product code must be greatest than 0", thrown.getMessage());
    }
	
}
