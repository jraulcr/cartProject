package com.cartproject.app.model.dto;

import java.time.LocalDateTime;

public class CartDTO {
	
    private Long cartId;
    private String description;
    private double amount;
    private LocalDateTime lastActivity;

    
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(LocalDateTime lastActivity) {
		this.lastActivity = lastActivity;
	}    
}
