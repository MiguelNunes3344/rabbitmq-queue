package com.br.consumerprice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.br.consumerprice.models.PriceModel;

public interface PriceRepository extends JpaRepository<PriceModel, Long> {
	
}
