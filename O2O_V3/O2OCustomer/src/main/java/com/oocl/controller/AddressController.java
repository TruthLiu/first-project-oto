package com.oocl.controller;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.pojo.Address;
import com.oocl.pojo.Customer;
import com.oocl.pojo.JSONResponse;
import com.oocl.service.AddressManager;

@Controller
@RequestMapping("/api/address")
public class AddressController {

	@Resource(name="addressManagerImpl")
	private AddressManager addressManager;
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> addAddress(String aname, HttpSession sess) {
		Customer c = (Customer) sess.getAttribute("loginCustomer");
		Address addr = addressManager.addAddress(new Address(UUID.randomUUID().toString(), aname), c.getId());
		if (addr == null) {
			return new JSONResponse<Address>(0, "Add failed", null, null);
		}
		return new JSONResponse<Address>(1, "Success!", addr, null);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public JSONResponse<?> findAllAddresses(HttpSession sess) {
		Customer c = (Customer) sess.getAttribute("loginCustomer");
		Set<Address> addrs = addressManager.findAddressesByCId(c.getId());
		return new JSONResponse<Address>(1, "Success!", null, addrs);
	}
	
	@RequestMapping(value="/{aid}", method=RequestMethod.DELETE)
	@ResponseBody
	public JSONResponse<?> deleteAddress(@PathVariable String aid) {
		Address addr = addressManager.deleteAddressById(aid);
		if (addr == null) {
			return new JSONResponse<Address>(0, "Delete failed", null, null);
		}
		return new JSONResponse<Address>(1, "Success!", null, null);
	}
}
