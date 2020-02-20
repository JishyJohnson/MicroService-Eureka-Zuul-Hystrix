package com.cart.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class Cart {
	
	private int cartId;
	private double totalPrice;
	
	private ArrayList<Product> cartItems = new ArrayList<Product>();
	
	private List<Integer> productIds=new ArrayList<Integer>();
	
	public List<Integer> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	public ArrayList<Product> getCartItems() {
		return cartItems;
	}
	public void setCartItems(ArrayList<Product> cartItems) {
		this.cartItems = cartItems;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	public void add(Product product) {
		cartItems.add(product);
	}

	public void remove(Product product) {
		List<Product>pr= getCartItems();
		int count=0;
		Iterator<Product> itr1 = pr.iterator();
		while (itr1.hasNext()) {
			Product itemObj = (Product) itr1.next();
			if (product.getPid() == itemObj.getPid()) {
				{
				cartItems.remove(count);
				break;
				}
				
			}
			else
				count=count+1;
		}

	}

}
