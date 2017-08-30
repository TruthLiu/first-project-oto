package com.oocl.service;

import java.util.List;

import com.oocl.pojo.Order;
import com.oocl.vo.OrderVO;

public interface OrderManager {
	
	 /**
	  * 加入order记录<br>
	  * 客户id=cId,商家id=mId
	  * */
	public Order addOrder(Order order,String cId,String mId);
	
	 /**
	  * 返回Id为cid的的用户的订单列表<br>
	  * 返回的List中的OrderVO只能访问自身基本属性成员和merchentVO成员
	  * */
	public List<OrderVO> findGeneralOrderList(String cid);
	
	/**
	  * 返回Id为oid的订单详细记录<br>
	  * 返回的OrderVO对象能访问自身所有属性成员<br>
	  * */
	public OrderVO findDetailedOrder(String oid);
	
	
	/**
	  * 确认ID为oid的订单已收货
	  * */
	public Order comfirmOrderReceived(String oid);
}
