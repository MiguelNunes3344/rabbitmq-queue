package com.br.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigTest {

    @Bean
    public RestTemplate createBeanRestTemplate() {
        return new RestTemplate();
    }
}
