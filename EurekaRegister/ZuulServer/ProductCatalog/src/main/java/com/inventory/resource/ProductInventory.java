package com.inventory.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.inventory.domain.Product;
import com.inventory.domain.Products;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/productcataolg-svc/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductInventory {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	Product prod;

	@Autowired
	Products products;

	@Autowired
	KafkaTemplate<String, Product> kafkaTemplate;

	@Autowired
	Gson jsonConverter;

	private static final String TOPIC = "Kafka_JSON";

	@GetMapping
	@HystrixCommand(fallbackMethod = "getFallBackCatalog")
	public Products getProduct() {

		Products product = restTemplate.getForObject("http://localhost:9090/inventory-svc/products", Products.class);

		return product;

		// return prod;
	}

	public Products getFallBackCatalog() {

		List<Product> productList = new ArrayList<Product>();
		prod.setPid(0);
		prod.setCount(0);
		prod.setPrice(0.00);
		prod.setName(null);
		productList.add(prod);
		products.setProducts(productList);
		return products;
	}

	@KafkaListener(topics = TOPIC)
	public Products getKafka(String prods) {
		System.out.println(prods);

		prod = jsonConverter.fromJson(prods, Product.class);

		System.out.println(prod.toString());


		List<Product> list = new ArrayList<Product>();
		list.add(prod);

		products.setProducts(list);
		return products;

	}

	@GetMapping("/test")
	public Products getProducts() {

		return products;

	}
}
