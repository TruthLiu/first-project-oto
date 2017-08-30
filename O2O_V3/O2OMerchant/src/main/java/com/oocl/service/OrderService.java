package com.oocl.service;

import java.util.List;

import com.oocl.pojo.Order;

public interface OrderService {
	/**
	 * id:merchatId
	 * */
	public List<OrderVO> findOrders(String id,int status);
	
	/**
	 * oId:orderId
	 * */
	public Order updateOrderState(String oId,int status);
}
