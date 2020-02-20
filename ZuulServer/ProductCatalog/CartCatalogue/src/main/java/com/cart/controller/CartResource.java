package com.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cart.model.Cart;
import com.cart.model.Carts;
import com.cart.model.Product;
import com.cart.service.ICartServices;

@RestController
@RequestMapping("/cartcatalogue-svc/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartResource {
	@Autowired
	private ICartServices cartservices;
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private Carts carts;

	@GetMapping
	public Carts getProduct() {
		return carts;
	}

	@PostMapping
	public Carts save(@RequestBody Product product) {
		return cartservices.saveCart(product);
	}

	@PutMapping
	public Carts update(@RequestBody Product product) {
		return cartservices.update(product);
	}

	@DeleteMapping("/{id}")
	public Carts removeItem(@PathVariable int id) {
		cartservices.remove(id);
		return carts;
	}
}
