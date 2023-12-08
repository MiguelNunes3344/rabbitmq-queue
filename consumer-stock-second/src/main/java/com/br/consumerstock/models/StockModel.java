package com.br.consumerstock.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StockModel  {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String productCode;
	@Column(nullable = false)
	private int quantity;
	
	public StockModel() {
		
	}

	
	public StockModel(UUID id, String productCode, int quantity) {
		this.id = id;
		this.productCode = productCode;
		this.quantity = quantity;
	}


	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}


	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Stock [productCode=" + productCode + ", quantity=" + quantity + "]";
	}
	
	
}

