package com.br.consumerprice.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PriceModel  {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private int productCode;
	@Column(nullable = false)
	private double price;
	
	public PriceModel() {
		
	}

	public PriceModel(UUID id, int productCode, double price) {
		this.id = id;
		this.productCode = productCode;
		this.price = price;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PriceModel [id=" + id + ", productCode=" + productCode + ", price=" + price + "]";
	}

	
	
	
}

