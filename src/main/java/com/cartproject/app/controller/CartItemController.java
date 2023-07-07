package com.cartproject.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartproject.app.model.dto.CartDTO;
import com.cartproject.app.model.entity.CartItem;
import com.cartproject.app.model.service.CartActivityService;
import com.cartproject.app.model.service.CartItemService;

@RestController
@RequestMapping("/cartItems")
public class CartItemController {
	
	private static final Logger LOGER = LogManager.getLogger(CartItemController.class);

	@Autowired
    private final CartItemService cartItemService;
	@Autowired
    private final CartActivityService cartActivityService;

    public CartItemController(CartItemService cartItemService, CartActivityService cartActivityService) {
        this.cartItemService = cartItemService;
        this.cartActivityService = cartActivityService;
    }

    @GetMapping("/all")
    public Map<String, Object> getAllData() {
        Map<String, Object> data = new HashMap<>();
        data.put("items", cartItemService.getAllItems());
        data.put("activities", cartActivityService.getAllActivities());
        return data;
    }

	@PostMapping("/create")
	public CartItem createCartItem(@RequestBody CartDTO cartDTO) {
		return cartItemService.saveCartItem(cartDTO);
	}

    @GetMapping("/getCart/{cartId}")
    public CartDTO getCartId(@PathVariable Long cartId) {
	    LOGER.info("Searching for the cart with id {}.", cartId);
	    return cartItemService.getCartItem(cartId);
    }

    @PutMapping("/update/{cartId}")
    public ResponseEntity<String> updateCartItem(@PathVariable Long cartId, @RequestBody CartDTO cartDTO) {
    	LOGER.info("Updating the item with id {}.", cartId);
    	cartItemService.updateCartItem(cartId, cartDTO);
	    String message = "Updated!";
	    return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long cartId) {
		LOGER.info("Deleting the item id {}.", cartId);
        cartItemService.deleteCartItem(cartId);
	    String message = "Deleted!";
	    return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
