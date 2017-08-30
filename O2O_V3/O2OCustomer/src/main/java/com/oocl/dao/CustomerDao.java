package com.oocl.dao;

import com.oocl.pojo.Customer;

public interface CustomerDao{
	public Customer addCustomer(Customer c);
	public Customer updateCustomer(Customer c);
	public Customer findCustomerByName(String cname);
	public Customer findCustomerByNameAndPwd(String cname,String cpwd);
}
