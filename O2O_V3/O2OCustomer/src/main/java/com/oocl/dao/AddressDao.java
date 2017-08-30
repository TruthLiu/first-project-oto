package com.oocl.dao;

import java.util.List;
import java.util.Set;

import com.oocl.pojo.Address;

public interface AddressDao {
	public Address addAddress(Address addr,String cid);
	public Set<Address> findAddressesByCId(String cid);
	public Address deleteAddressById(String aid);
	public Address updateAddress(Address addr);
}
