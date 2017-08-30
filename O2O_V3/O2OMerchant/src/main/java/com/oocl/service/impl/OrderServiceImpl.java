package com.oocl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.OrderDao;
import com.oocl.pojo.Order;
import com.oocl.service.OrderService;
import com.oocl.util.VOUtil;
import com.oocl.vo.OrderVO;

@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService{
	
	@Resource(name="orderDaoImpl")
	private OrderDao orderDao;

	@Override
	public List<OrderVO> findOrders(String id, int status) {
		OrderVO orderVO;
		List<Order> orders = orderDao.findOrders(id, status);
		List<OrderVO> orderVOs = new ArrayList<OrderVO>();
		for(Order order:orders){
			orderVO = VOUtil.OrderPOToVO(order);
			orderVOs.add(orderVO);
		}
		return orderVOs;
	}

	@Transactional
	@Override
	public Order updateOrderState(String oId, int status) {
		// TODO Auto-generated method stub
		return orderDao.updateOrderState(oId, status);
	}

	
	
}
