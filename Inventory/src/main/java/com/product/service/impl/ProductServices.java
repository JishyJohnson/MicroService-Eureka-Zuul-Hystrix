package com.product.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.product.model.Order;
import com.product.model.Product;
import com.product.model.Products;
import com.product.repo.ProductRepo;
import com.product.service.IProductServices;

@Service
public class ProductServices implements IProductServices {

	@Autowired
	private ProductRepo prodRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Product products;

	public List<Product> getAllProduct() {
		return (List<Product>) prodRepo.findAll();

	}
	public Optional<Product> getProductById(int id) {
		return prodRepo.findById(id);
		
	}
 

	public Product saveProduct(Product product) {
		return prodRepo.save(product);

	}


	

	@Override
	public Product delete() {
		Order order = restTemplate.getForObject("http://localhost:8084/ordermangemnt-svc/order", Order.class);
		List<Integer> prodList=order.getProdList();
		Iterator<Integer> itr1 = prodList.iterator();
		while(itr1.hasNext()) {
			int prodId=itr1.next();
			Optional<Product> product = prodRepo.findById(prodId);
			if (product.isPresent()) {
				int p = product.get().getCount();
				products.setPid(product.get().getPid());
				products.setName(product.get().getName());
				products.setPrice(product.get().getPrice());
				products.setCount(p-1);
				prodRepo.save(products);
		}
			
		
	
		}
		return products;
	}

}
