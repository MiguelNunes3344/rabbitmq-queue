package com.br.stock.dto;

import java.io.Serializable;

public class PriceDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int productCode;
	private double price;
	
	public PriceDto() {
		
	}

	public PriceDto(int productCode, double price) {
		this.productCode = productCode;
		this.price = price;
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
	
	
	
}
