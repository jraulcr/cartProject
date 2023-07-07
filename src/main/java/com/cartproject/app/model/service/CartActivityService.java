package com.cartproject.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartproject.app.model.entity.CartActivity;
import com.cartproject.app.model.repository.ICartActivityRepository;

@Service
public class CartActivityService {

	@Autowired
    private final ICartActivityRepository cartActivityRepository;

    public CartActivityService(ICartActivityRepository cartActivityRepository) {
        this.cartActivityRepository = cartActivityRepository;
    }    

    public List<CartActivity> getAllActivities() {
        return cartActivityRepository.findAll();
    }
    
    public CartActivity saveCartActivity(CartActivity cartActivity) {
        return cartActivityRepository.save(cartActivity);
    }

    public CartActivity createCartActivity(CartActivity cartActivity) {
        return cartActivityRepository.save(cartActivity);
    }

    public CartActivity getCartActivity(Long activityId) {
        return cartActivityRepository.findById(activityId).orElse(null);
    }

    public CartActivity updateCartActivity(Long activityId, CartActivity cartActivity) {
        CartActivity existingCartActivity = cartActivityRepository.findById(activityId).orElse(null);
        if (existingCartActivity != null) {
            existingCartActivity.setLastActivity(cartActivity.getLastActivity());
            return cartActivityRepository.save(existingCartActivity);
        }
        return null;
    }

    public void deleteCartActivity(Long activityId) {
        cartActivityRepository.deleteById(activityId);
    }

}
