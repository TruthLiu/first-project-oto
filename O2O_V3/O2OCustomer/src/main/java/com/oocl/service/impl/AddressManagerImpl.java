package com.oocl.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.AddressDao;
import com.oocl.pojo.Address;
import com.oocl.service.AddressManager;

@Service("addressManagerImpl")
public class AddressManagerImpl implements AddressManager {

	@Resource(name = "addressDaoImpl")
	private AddressDao addressDao;
	
	@Override
	@Transactional
	public Address addAddress(Address addr, String cid) {
		return addressDao.addAddress(addr, cid);
	}

	@Override
	@Transactional
	public Set<Address> findAddressesByCId(String cid) {
		return addressDao.findAddressesByCId(cid);
	}

	@Override
	@Transactional
	public Address deleteAddressById(String aid) {
		return addressDao.deleteAddressById(aid);
	}

	@Override
	public Address updateAddress(Address addr) {
		return null;
	}

}
