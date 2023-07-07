package com.cartproject.app.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cartproject.app.model.dto.CartDTO;
import com.cartproject.app.model.entity.CartActivity;
import com.cartproject.app.model.entity.CartItem;
import com.cartproject.app.model.repository.ICartActivityRepository;
import com.cartproject.app.model.repository.ICartItemRepository;

@Service
public class CartItemService {

	@Autowired
    private final ICartItemRepository cartItemRepository;
    
    @Autowired
    private final ICartActivityRepository cartActivityRepository;

    public CartItemService(ICartItemRepository cartItemRepository, ICartActivityRepository cartActivityRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartActivityRepository = cartActivityRepository;
    }

    public List<CartItem> getAllItems() {
        return cartItemRepository.findAll();
    }
    
    public CartItem saveCartItem(CartDTO cartDTO) {
        CartActivity cartActivity = new CartActivity();
        cartActivity.setLastActivity(LocalDateTime.now());

        cartActivity = cartActivityRepository.save(cartActivity);

        CartItem cartItem = new CartItem();
        cartItem.setDescription(cartDTO.getDescription());
        cartItem.setAmount(cartDTO.getAmount());
        cartItem.setCartActivity(cartActivity);

        return cartItemRepository.save(cartItem);
    }

    public CartItem createCartItem(CartDTO cartDTO) {
    	CartItem cartItem = new CartItem();
		
    	cartItem.setCartId(cartDTO.getCartId());
    	cartItem.setDescription(cartDTO.getDescription());
    	cartItem.setAmount(cartDTO.getAmount());
		
        return cartItemRepository.save(cartItem);
    }

	public CartDTO getCartItem(Long cartId) {

		Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartId);

		if (optionalCartItem.isPresent()) {
			CartItem cartItem = optionalCartItem.get();

			// Crear una instancia de CartDTO y asignar los valores de los campos
			CartDTO cartDTO = new CartDTO();
			cartDTO.setCartId(cartItem.getCartId());
			cartDTO.setDescription(cartItem.getDescription());
			cartDTO.setAmount(cartItem.getAmount());
			cartDTO.setLastActivity(cartItem.getCartActivity().getLastActivity());
			return cartDTO;
		} else {
			return null;
		}
	}

    public CartItem updateCartItem(Long cartId, CartDTO cartDTO) {
        CartItem existingCartItem = cartItemRepository.findById(cartId).orElse(null);
        if (existingCartItem != null) {
            existingCartItem.setDescription(cartDTO.getDescription());
            existingCartItem.setAmount(cartDTO.getAmount());
            return cartItemRepository.save(existingCartItem);
        }
        return null;
    }

    public boolean deleteCartItem(Long cartId) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartId);
        if (cartItemOptional.isPresent()) {
            cartItemRepository.deleteById(cartId);
            return true;
        }
        return false;
    }

    @Scheduled(fixedDelay = 600000) // Ejecutar cada minuto
    @Transactional // Asegura que se inicie una transacción antes de ejecutar el método
    public void deleteInactiveCartsScheduled() {
        deleteInactiveCarts();
    }

    @Transactional
    public void deleteInactiveCarts() {
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
        cartItemRepository.deleteAllByCartActivityLastActivityBefore(tenMinutesAgo);
    }
}
