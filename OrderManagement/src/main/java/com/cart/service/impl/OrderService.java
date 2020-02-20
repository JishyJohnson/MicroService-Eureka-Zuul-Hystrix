package com.cart.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cart.model.Cart;
import com.cart.model.Carts;
import com.cart.model.Order;
import com.cart.model.Product;
import com.cart.model.Products;
import com.cart.service.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Order order;

	@Override
	public Order checkOut() {
		Carts cartItems = restTemplate.getForObject("http://localhost:9092/cartcatalogue-svc/cart", Carts.class);
		// List<Integer> prodList=cartItems.getProductIds();
		order.setProdList(cartItems.getProductIds());
		order.setTotalprice(cartItems.getTotalPrice());
		order.setOrderId(1001);

	//	restTemplate.delete("http://localhost:8080/inventory-svc/products");
		List<Integer> prodList = order.getProdList();
		Iterator<Integer> itr1 = prodList.iterator();
		while (itr1.hasNext()) {
			int prodId = itr1.next();
			restTemplate.delete("http://localhost:9092/cartcatalogue-svc/cart/" + prodId);
			restTemplate.delete("http://localhost:9090/inventory-svc/products");
			
		}

		return order;
	}

	public Order getOrder() {

		return order;
	}

}
