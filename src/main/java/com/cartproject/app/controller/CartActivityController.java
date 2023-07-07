package com.cartproject.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartproject.app.model.entity.CartActivity;
import com.cartproject.app.model.service.CartActivityService;

@RestController
@RequestMapping("/cartActivities")
public class CartActivityController {

	private static final Logger LOGER = LogManager.getLogger(CartActivityController.class);

	@Autowired
    private final CartActivityService cartActivityService;

    public CartActivityController(CartActivityService cartActivityService) {
        this.cartActivityService = cartActivityService;
    }

    @GetMapping("/getActivity/{activityId}")
    public CartActivity getCartActivity(@PathVariable Long activityId) {
	    LOGER.info("Buscando el superhéroe con id {}.", activityId);
	    return cartActivityService.getCartActivity(activityId);
    }

    @DeleteMapping("/deleteActivity/{activityId}")
    public ResponseEntity<String> deleteCartActivity(@PathVariable Long activityId) {
        cartActivityService.deleteCartActivity(activityId);
	    String message = "Deleted!";
	    return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
