package com.oocl.dao.impl;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.loader.custom.Return;
import org.springframework.stereotype.Repository;

import com.oocl.dao.AddressDao;
import com.oocl.pojo.Address;
import com.oocl.pojo.Customer;

@Repository(value = "addressDaoImpl")
public class AddressDaoImpl implements AddressDao{

	@PersistenceContext(name="punit")
	private EntityManager entitymanager;
	
	@Override
	public Address addAddress(Address addr, String cid) {
		Customer customer = (Customer)entitymanager.find(Customer.class, cid);
		addr.setCustomer(customer);
		entitymanager.persist(addr);
		return addr;
	}

	@Override
	public Set<Address> findAddressesByCId(String cid) {
		Customer customer = (Customer)entitymanager.find(Customer.class, cid);
		Set<Address> Caddrs = customer.getCaddrs();
		Caddrs.isEmpty();
		return Caddrs;
	}

	@Override
	public Address deleteAddressById(String aid) {
		Address address = (Address)entitymanager.find(Address.class, aid);
		entitymanager.remove(address);
		return address;
	}

	@Override
	public Address updateAddress(Address addr) {
		return null;
	}
	
}
