package com.enterprise.ordermanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderRequest {
	
	@NotNull(message = "Customer ID is required")
	private Long customerId;
	
	@NotNull(message = "product ID is required")
	private Long productId;
	
	@NotNull(message = "Quantity is required")
	@Min(value=1, message="Quantity must be at least 1")
	private int quantity;

	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
