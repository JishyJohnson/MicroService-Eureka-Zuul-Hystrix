package com.product.service;

import java.util.List;
import java.util.Optional;

import com.product.model.Product;


public interface IProductServices {
	List<Product> getAllProduct();
	Optional<Product> getProductById(int id);
	Product saveProduct(Product product);
	Product delete();
}
