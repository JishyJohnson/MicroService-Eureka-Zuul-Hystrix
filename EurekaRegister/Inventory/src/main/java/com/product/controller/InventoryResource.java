package com.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StreamUtils;
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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.product.CrossOrigin;
import com.product.model.Product;
import com.product.model.Products;
import com.product.service.IProductServices;

@RestController
@RequestMapping("/inventory-svc/products")
@CrossOrigin(origins = "http://localhost:4200")
//@GetMapping("/yourPath")
public class InventoryResource {
	@Autowired
	IProductServices prodservice;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Products products;

	@Autowired
	Product prod;

	@Autowired
	KafkaTemplate<String, Product> kafkaTemplate;

	/*
	 * @GetMapping("/img") public ResponseEntity<byte[]> getImage() throws
	 * IOException {
	 * 
	 * ClassPathResource imgFile = new ClassPathResource("images/mobile.jpg");
	 * 
	 * // response.setContentType(MediaType.IMAGE_JPEG_VALUE); //
	 * StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	 * byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
	 * 
	 * return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
	 * 
	 * // return ResponseEntity.ok().
	 * 
	 * 
	 * }
	 */

	private static final String TOPIC = "Kafka_JSON";

	@GetMapping("/{pid}")
	public Optional<Product> getProductById(@PathVariable int pid) {
		Optional<Product> prodList = prodservice.getProductById(pid);
		return prodList;
	}

	@GetMapping
	@HystrixCommand(fallbackMethod = "getFallBackProductCatalog")
	public Products getProduct() {

		products.setProducts(prodservice.getAllProduct());
		return products;
	}

	public Products getFallBackProductCatalog() {

		List<Product> productList = new ArrayList<Product>();
		prod.setPid(0);
		prod.setCount(0);
		prod.setPrice(0.00);
		prod.setName(null);
		productList.add(prod);
		products.setProducts(productList);
		return products;
	}

	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		kafkaTemplate.send(TOPIC, product);
		return prodservice.saveProduct(product);
	}

	@PutMapping
	public Product update(@RequestBody Product product) {
		return prodservice.saveProduct(product);
	}

	@DeleteMapping
	public Product delete() {
		/**
		 * TODO: Checkout cart =
		 * restTemplate.getForObject("http://localhost:8084/order", Checkout.class);
		 * Remove the product after checkout and update the count
		 */
		// products.setProducts(prodservice.delete());

		return prodservice.delete();
	}

}
