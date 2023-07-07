package com.cartproject.app.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cart_activities")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartActivity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 303963987979968971L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id", unique = true, nullable = false)
    private Long activityId;
    
    @Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastActivity; 
    
    @OneToMany(mappedBy = "cartActivity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;
	
    public void updateLastActivity() {
        lastActivity = LocalDateTime.now();
    }
	
	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public LocalDateTime getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(LocalDateTime lastActivity) {
		this.lastActivity = lastActivity;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}	
}
