package com.oocl.vo;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.oocl.pojo.Order;

public class OrderItemVO {

	private String id;
	
	private String dishName;
	
	private int num;
	
	private double unitPrice;

	
	
	public OrderItemVO() {
		super();
	}

	public OrderItemVO(String id, String dishName, int num, double unitPrice) {
		super();
		this.id = id;
		this.dishName = dishName;
		this.num = num;
		this.unitPrice = unitPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	
}
