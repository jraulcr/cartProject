package com.cartproject.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cartproject.app.model.entity.CartActivity;

public interface ICartActivityRepository extends JpaRepository<CartActivity, Long> {}
