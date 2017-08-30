package com.oocl.dao;

import java.util.List;

import com.oocl.pojo.Order;

public interface OrderDao {
	
	/**
	 * id:merchatId
	 * */
	public List<Order> findOrders(String id,int status);

	/**
	 * oId:orderId
	 * */
	public Order updateOrderState(String oId,int status);
	
}
