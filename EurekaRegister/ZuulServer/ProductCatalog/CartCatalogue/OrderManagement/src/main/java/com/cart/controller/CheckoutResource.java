package com.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cart.model.Cart;
import com.cart.model.Order;
import com.cart.model.Products;
import com.cart.service.IOrderService;

@RestController
@RequestMapping("/ordermangemnt-svc/order")
@CrossOrigin(origins = "http://localhost:4200")
public class CheckoutResource {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	IOrderService orderService;
	

	@PostMapping
	public Order createOrder() {
		return orderService.checkOut();
	}

	@GetMapping
	public Order getOrder() {
		return orderService.getOrder();
	}

}
