package com.cart.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
	
	private int pid;
	private String name;
	


	public Product() {
	}

	public Product(int pid, String name) {
		super();
		this.pid = pid;
		this.name = name;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
