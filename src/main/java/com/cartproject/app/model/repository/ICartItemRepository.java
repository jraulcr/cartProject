package com.cartproject.app.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cartproject.app.model.entity.CartItem;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
	
	List<CartItem> findAll();

	void deleteAllByCartActivityLastActivityBefore(LocalDateTime lastActivity);
}
