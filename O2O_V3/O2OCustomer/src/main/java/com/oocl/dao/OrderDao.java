package com.oocl.dao;

import java.util.Set;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.oocl.pojo.Order;

public interface OrderDao {
	public Order addOrder(Order order,String cId,String mId);
	public Set<Order> findOrdersByCId(String CId);
	public Set<Order> findOrdersByMId(String MId);
	public Order findOrderByOId(String OId);
	public Order comfirmOrderReceived(String oid);
}
