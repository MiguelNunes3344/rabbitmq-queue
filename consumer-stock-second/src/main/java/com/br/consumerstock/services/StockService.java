package com.br.consumerstock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.consumerstock.models.StockModel;
import com.br.consumerstock.repository.StockRepository;

@Service
public class StockService {

	@Autowired
	StockRepository stockRepository;
	
	public void save(StockModel stockModel) {
		stockRepository.save(stockModel);
	}
}
