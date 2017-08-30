package com.oocl.service;

import java.util.List;
import java.util.Set;

import com.oocl.pojo.Address;

public interface AddressManager {
	
	/**
	 * 加入Address记录addr，并将其关联到Id为cid的用户
	 * */
	public Address addAddress(Address addr,String cid);
	
	/**
	 * 找出用户Id为cid的Address记录列表
	 * */
	public Set<Address> findAddressesByCId(String cid);
	
	/**
	 * 根据Id删除数据库中某Address记录
	 * */
	public Address deleteAddressById(String aid);
	public Address updateAddress(Address addr);
}
