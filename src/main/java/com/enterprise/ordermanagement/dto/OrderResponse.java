package com.enterprise.ordermanagement.dto;

import java.time.LocalDateTime;

public class OrderResponse {
	
	private Long Id;
	private Long customerId;
	private Long productId;
	private int quantity;
	private LocalDateTime createdAt;
	private String status;
	
	public OrderResponse(Long id, Long customerId, Long productId, int quantity, LocalDateTime createdAt,
			String status) {
		
		Id = id;
		this.customerId = customerId;
		this.productId = productId;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.status = status;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
