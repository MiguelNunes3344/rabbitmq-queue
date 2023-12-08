package com.br.consumerstock.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.consumerstock.models.StockModel;

public interface StockRepository extends JpaRepository<StockModel, UUID> {

}
