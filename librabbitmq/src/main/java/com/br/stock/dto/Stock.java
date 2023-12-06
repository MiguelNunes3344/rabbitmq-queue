package com.br.stock.dto;

import java.io.Serializable;

public class Stock implements Serializable {


	private static final long serialVersionUID = 1L;
	private String productCode;
	private int quantity;
	
	public Stock() {
		
	}

	public Stock(String productCode, int quantity) {
		this.productCode = productCode;
		this.quantity = quantity;
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
