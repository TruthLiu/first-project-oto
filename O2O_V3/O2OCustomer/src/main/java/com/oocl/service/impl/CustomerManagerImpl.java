package com.oocl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.CustomerDao;
import com.oocl.pojo.Customer;
import com.oocl.service.CustomerManager;

@Service("customerManagerImpl")
public class CustomerManagerImpl implements CustomerManager {

	@Resource(name = "customerDaoImpl")
	private CustomerDao customerDao;
	
	@Override
	@Transactional
	public Customer addCustomer(Customer c) {
		return customerDao.addCustomer(c);
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer c) {
		return customerDao.updateCustomer(c);
	}

	@Override
	public Customer findCustomerByName(String cname) {
		return customerDao.findCustomerByName(cname);
	}

	@Override
	public Customer findCustomerByNameAndPwd(String cname, String cpwd) {
		return customerDao.findCustomerByNameAndPwd(cname, cpwd);
	}

}
