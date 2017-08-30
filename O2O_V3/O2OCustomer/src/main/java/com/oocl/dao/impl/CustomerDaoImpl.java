package com.oocl.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import oracle.jdbc.driver.OracleConnection;
import oracle.sql.CustomDatum;
import oracle.sql.Datum;

import com.oocl.dao.CustomerDao;
import com.oocl.pojo.Customer;

@Repository(value = "customerDaoImpl")
public class CustomerDaoImpl implements CustomerDao {
	
	@PersistenceContext(name="punit")
	private EntityManager entitymanager;
	
	@Override
	public Customer addCustomer(Customer c) {
		entitymanager.persist(c);
		return c;
	}

	@Override
	public Customer updateCustomer(Customer c) {
		entitymanager.merge(c);
		return c;
	}

	@Override
	public Customer findCustomerByName(String cname) {
		String jpql="select c from Customer c where c.cname=:cname";
		List<Customer> customerList = entitymanager.createQuery(jpql)
				.setParameter("cname", cname)
				.getResultList();
		if(customerList.isEmpty())return null;
		else return customerList.get(0);
	}

	@Override
	public Customer findCustomerByNameAndPwd(String cname, String cpwd) {
		String jpql="select c from Customer c where c.cname=:cname and c.cpwd=:cpwd";
		List<Customer> customerList = entitymanager.createQuery(jpql)
				.setParameter("cname", cname)
				.setParameter("cpwd", cpwd)
				.getResultList();
		if(customerList.isEmpty())return null;
		else return customerList.get(0);
	}


}
