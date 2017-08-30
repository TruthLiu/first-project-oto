package com.oocl.service;

import com.oocl.pojo.Customer;

public interface CustomerManager {
	
	/**
	 * 加入新用户<br>
	 * */
	public Customer addCustomer(Customer c);
	
	/**
	 * 用于更新用户信息<br>
	 * */
	public Customer updateCustomer(Customer c);
	
	/**
	 * 用于注册验证<br>
	 * 当用户名已存在,返回对象<br> 
	 * 当用户名不存在，返回null(则该用户名可注册)<br> 
	 * */
	public Customer findCustomerByName(String cname);
	
	/**
	 * 用于登录验证<br>
	 * 当用户名和密码正确，返回用户对象<br> 
	 * 当用户名不存在或密码错误，返回null<br> 
	 * */
	public Customer findCustomerByNameAndPwd(String cname,String cpwd);
}
