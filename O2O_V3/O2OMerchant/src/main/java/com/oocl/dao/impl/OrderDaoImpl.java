package com.oocl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oocl.dao.OrderDao;
import com.oocl.pojo.Comment;
import com.oocl.pojo.Order;

@Repository(value="orderDaoImpl")
public class OrderDaoImpl implements OrderDao{
	
	/**
	 * order-state:
	 * reject：-1
	 * waiting：0
	 * receive：1
	 * send:2
	 * finish:3
	 * */
	private Logger logger = Logger.getLogger(OrderDaoImpl.class);
	@PersistenceContext(name="punit")
	private EntityManager em;

	

	@Override
	public Order updateOrderState(String oId, int status) {
		Order order = em.find(Order.class, oId);
		order.setStatus(status);
		em.persist(order);
		return order;
	}

	@Override
	public List<Order> findOrders(String id, int status) {
		String jpql="select o from Order o where o.merchant.id=:id and o.status=:status order by o.createTime desc";
		List<Order> ls=em.createQuery(jpql)
				.setParameter("id", id)
				.setParameter("status", status)
				.getResultList();
		return ls;
	}

}
