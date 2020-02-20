package com.cart.service;

import java.util.List;

import com.cart.model.Cart;
import com.cart.model.Carts;
import com.cart.model.Product;
import com.cart.model.Products;


public interface ICartServices {
	public Carts getAllCart();
	public Carts saveCart(Product product);
	public Carts update(Product product);
	public int remove(int id);
	
}
