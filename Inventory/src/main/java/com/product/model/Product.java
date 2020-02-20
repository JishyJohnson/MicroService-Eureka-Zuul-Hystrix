package com.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="product")
@Component
public class Product {
	@Id
	@Column(name="pid")
	private int pid;
	
	private String name;
	private int count;
	private double price;
	
	public Product(){}
	
	
	
	public Product(int pid, String name, int count, double price) {
		super();
		this.pid = pid;
		this.name = name;
		this.count = count;
		this.price = price;
	}



	public Product(int pid, String name) {
		super();
		this.pid = pid;
		this.name = name;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	

}
