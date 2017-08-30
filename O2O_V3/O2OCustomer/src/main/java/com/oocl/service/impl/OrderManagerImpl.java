package com.oocl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.AddressDao;
import com.oocl.dao.OrderDao;
import com.oocl.pojo.Order;
import com.oocl.service.OrderManager;
import com.oocl.util.VOUtil;
import com.oocl.vo.OrderVO;

@Service("orderManagerImpl")
public class OrderManagerImpl implements OrderManager {

	@Resource(name = "orderDaoImpl")
	private OrderDao orderDao;
	
	@Override
	@Transactional
	public Order addOrder(Order order, String cId, String mId) {
		return orderDao.addOrder(order, cId, mId);
	}

	@Override
	@Transactional
	public List<OrderVO> findGeneralOrderList(String cid) {
		OrderVO orderVO;
		List<OrderVO> orderVOs = new ArrayList<OrderVO>();
		Set<Order> orders = orderDao.findOrdersByCId(cid);
		for(Order order:orders){
			orderVO = VOUtil.OrderPOToVO2(order);
			orderVOs.add(orderVO);
		}
		return orderVOs;
	}

	@Override
	@Transactional
	public OrderVO findDetailedOrder(String oid) {
		Order order = orderDao.findOrderByOId(oid);
		return VOUtil.OrderPOToVO(order);
	}

	@Override
	@Transactional
	public Order comfirmOrderReceived(String oid) {
		return orderDao.comfirmOrderReceived(oid);
	}

}
