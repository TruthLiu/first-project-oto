package com.oocl.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.oocl.dao.ComplaintDao;
import com.oocl.pojo.Complaint;
import com.oocl.pojo.Order;


@Repository(value = "complaintDaoImpl")
public class ComplaintDaoImpl implements ComplaintDao {

	@PersistenceContext(name="punit")
	private EntityManager entitymanager;
	
	@Override
	public Complaint addComplaint(Complaint complaint, String orderId) {
		Order order = entitymanager.find(Order.class, orderId);
		if(order.getComplaint()!=null)return null;
		else{
			complaint.setOrder(order);
			complaint.setCustomer(order.getCustomer());
			complaint.setMerchant(order.getMerchant());
			entitymanager.persist(complaint);
			return complaint;
		}
	}

}
