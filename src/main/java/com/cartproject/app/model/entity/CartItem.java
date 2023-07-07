package com.cartproject.app.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cart_items")
public class CartItem implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6985086294223510956L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", unique = true, nullable = false)
    private Long cartId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double amount;     

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    //@JsonIgnore
    @JsonBackReference // Evita la serialización en bucle
    private CartActivity cartActivity;

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

	public CartActivity getCartActivity() {
		return cartActivity;
	}

	public void setCartActivity(CartActivity cartActivity) {
		this.cartActivity = cartActivity;
	} 
}
