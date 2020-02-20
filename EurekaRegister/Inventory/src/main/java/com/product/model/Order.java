package com.product.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Order {
	
	private int orderId;
	//private int pid;
	private List<Integer> prodList=new ArrayList<Integer>();
	private double totalprice;
	private int count;
	
	
	public List<Integer> getProdList() {
		return prodList;
	}
	public void setProdList(List<Integer> prodList) {
		this.prodList = prodList;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/*
	 * public int getPid() { return pid; } public void setPid(int pid) { this.pid =
	 * pid; }
	 */
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}
