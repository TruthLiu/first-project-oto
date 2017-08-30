package com.oocl.dao.impl;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.loader.custom.Return;
import org.springframework.stereotype.Repository;

import com.oocl.dao.OrderDao;
import com.oocl.pojo.Customer;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Order;
import com.oocl.pojo.OrderItem;


@Repository(value = "orderDaoImpl")
public class OrderDaoImpl implements OrderDao {

	@PersistenceContext(name="punit")
	private EntityManager entitymanager;
	
	@Override
	public Order addOrder(Order order,String customerId,String merchantId) {
		OrderItem oi ;
		Customer customer = entitymanager.find(Customer.class, customerId);
		Merchant merchant = entitymanager.find(Merchant.class,merchantId);
		order.setCustomer(customer);
		order.setMerchant(merchant);
		Set<OrderItem> items = order.getItems();
		
		entitymanager.persist(order);
		for(OrderItem item:items){
			entitymanager.persist(item);
		}
		entitymanager.flush();
		entitymanager.refresh(order);
		return order;
	}

	@Override
	public Set<Order> findOrdersByCId(String CId) {
		return (Set<Order>)entitymanager.find(Customer.class, CId).getOrders();
	}

	@Override
	public Set<Order> findOrdersByMId(String MId) {
		return (Set<Order>)entitymanager.find(Merchant.class, MId).getOrder();
	}

	@Override
	public Order findOrderByOId(String OId) {
		return entitymanager.find(Order.class, OId);
	}

	@Override
	public Order comfirmOrderReceived(String oid) {
		String jpql = "Update Order o set o.status =:status where o.id =:oid";
		entitymanager.createQuery(jpql)
		.setParameter("status", 3)
		.setParameter("oid", oid)
		.executeUpdate();
		entitymanager.flush();
		return entitymanager.find(Order.class, oid);
	}
	
	

}
