package com.br.consumerprice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.consumerprice.models.PriceModel;
import com.br.consumerprice.repository.PriceRepository;

@Service
public class PriceService {

	@Autowired
	PriceRepository priceRepository;
	
	
	public PriceModel savePrice(PriceModel priceModel) {
		return priceRepository.save(priceModel);
	}
	
}
