package com.oocl.controller;

import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.pojo.CartItem;
import com.oocl.pojo.Customer;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Order;
import com.oocl.pojo.OrderItem;
import com.oocl.service.MerchantManager;
import com.oocl.service.OrderManager;

@Controller
public class PayController {
	
	@Resource(name="merchantManagerImpl")
	private MerchantManager merchantManager;
	
	@Resource(name="orderManagerImpl")
	private OrderManager orderManager;

	@RequestMapping(value="/pay", method=RequestMethod.GET)
	public String showPayPage(Model model, HttpSession sess) {
		String currentMerchantId = (String) sess.getAttribute("currentMerchantId");
		if (currentMerchantId != null) {
			Merchant m = merchantManager.findOneMerchantById(currentMerchantId);
			model.addAttribute("merchant", m);
		}
		
		// calculate total price
		double totalPrice = 0D;
		@SuppressWarnings("unchecked")
		Map<String, CartItem> cartItems = (Map<String, CartItem>) sess.getAttribute("cart");
		if (cartItems != null) {
			for (CartItem item : cartItems.values()) {
				totalPrice += item.getPrice() * item.getCount();
			}
		}
		model.addAttribute("totalPrice", totalPrice);
		
		return "pay";
	}
	
	@RequestMapping(value="/api/pay", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> doPay(String mid, String realName, String phone, String addr, Double totalPrice, HttpSession sess) {
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setAddr(addr);
		order.setRealName(realName);
		order.setPhone(phone);
		order.setCreateTime(Calendar.getInstance().getTime());
		order.setStatus(0);
		
		// add cart items into order
		@SuppressWarnings("unchecked")
		Map<String, CartItem> cartItems = (Map<String, CartItem>) sess.getAttribute("cart");
		if (cartItems == null || cartItems.isEmpty()) {
			return new JSONResponse<Order>(0, "Nothing in cart!", null, null);
		}
		
//		double totalPrice = 0D;
		for (CartItem item : cartItems.values()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setId(UUID.randomUUID().toString());
			orderItem.setDishName(item.getdName());
			
			orderItem.setUnitPrice(item.getPrice());
			orderItem.setNum(item.getCount());
//			totalPrice += item.getPrice() * item.getCount();
			
			orderItem.setOrder(order);
			order.getItems().add(orderItem);
		}
		
		order.setTotalPrice(totalPrice);
		
		Customer c = (Customer) sess.getAttribute("loginCustomer");
		
		Customer orderC = new Customer();
		orderC.setId(c.getId());
		order.setCustomer(orderC);
		
		Merchant orderM = new Merchant();
		orderM.setId(mid);
		order.setMerchant(orderM);
		
//		Order o = null;
		Order o = orderManager.addOrder(order, c.getId(), mid);
		
		if (o == null) {
			return new JSONResponse<String>(0, "Failed!", null, null);
		}
		
//		cartItems.clear();
		
		return new JSONResponse<String>(1, "Success!", null, null);
	}
}
