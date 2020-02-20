package com.inventory.domain;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Products {
	List<Product> Products;

	public List<Product> getProducts() {
		return Products;
	}

	public void setProducts(List<Product> products) {
		Products = products;
	}

}
