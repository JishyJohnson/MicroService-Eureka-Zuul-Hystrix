package com.cart.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cart.model.Cart;
import com.cart.model.Carts;
import com.cart.model.Product;
import com.cart.model.Products;
import com.cart.service.ICartServices;

@Service
public class CartServices implements ICartServices {

	@Autowired
	Cart cart;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Carts carts;
	
	/*
	 * public List<Product> getAllCart() { return cart.getCartItems(); }
	 */
	
	public Carts getAllCart() {
		return carts;
	}

	public Carts saveCart(Product prod) {
		Products productslist = restTemplate.getForObject("http://localhost:9098/productcataolg-svc/products", Products.class);
		List<Product> itemlist = productslist.getProducts();
		List<Integer> prodIdList=cart.getProductIds();
		double totalprice=0.0;
		Iterator<Product> itr1 = (Iterator<Product>) itemlist.iterator();
		boolean found = false;
		while (itr1.hasNext()) {
			Product itemObj = (Product) itr1.next();
			if (prod.getPid() == itemObj.getPid()) {
				prodIdList.add(prod.getPid());
				found = true;
				totalprice=totalprice+itemObj.getPrice()+carts.getTotalPrice();
				carts.setTotalPrice(totalprice);
				carts.setProductIds(prodIdList);
				cart.add(itemObj);
			}
		}

		if (found != true)
			System.out.println("Selected Code is Invalid....Please choose correct Item code!! ");
		return carts;

	}

	public int remove(int id ) {
		
		boolean found = false;
		List<Product> itemlist=cart.getCartItems();
		Iterator<Product> itr1 = itemlist.iterator();
		List<Integer> prodIdList=cart.getProductIds();
		int count=0;
		while (itr1.hasNext()) {
			Product itemObj = (Product) itr1.next();
			if (id == itemObj.getPid()) {
				found = true;
				prodIdList.remove(count);
				carts.setTotalPrice(carts.getTotalPrice()-itemObj.getPrice());
				carts.setProductIds(prodIdList);
				cart.remove(itemObj);
				break;
			}
			else
				count=count+1;
		}
		if (found != true) {
			throw new RuntimeException("Unknown product Id.. ");
		}

		else {
			System.out.println("\nSelected product is removed \n");
			return 1;
		}

	}
	
	public Carts update(Product prod) {
		Products productslist = restTemplate.getForObject("http://localhost:9098/productcataolg-svc/products", Products.class);
		List<Product> itemlist = productslist.getProducts();
		Iterator<Product> itr1 = (Iterator<Product>) itemlist.iterator();
		List<Integer> prodIdList=cart.getProductIds();
		boolean found = false;
		double totalprice=0.0;
		while (itr1.hasNext()) {
			Product itemObj = (Product) itr1.next();
			if (prod.getPid() == itemObj.getPid()) {
				found = true;
			//	cart.setProductIds(prodIdList);
				prodIdList.add(prod.getPid());
				totalprice=totalprice+itemObj.getPrice()+carts.getTotalPrice();
			//	cart.setTotalPrice(totalprice);
				carts.setProductIds(prodIdList);
				carts.setTotalPrice(totalprice);
				cart.add(itemObj);
			}
		}

		if (found != true)
			System.out.println("Selected Code is Invalid....Please choose correct Item code!! ");
		return carts;

	} 

	
}
