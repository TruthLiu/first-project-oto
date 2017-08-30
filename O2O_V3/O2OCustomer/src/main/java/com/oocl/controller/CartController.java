package com.oocl.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.pojo.CartItem;
import com.oocl.pojo.Dish;
import com.oocl.pojo.JSONResponse;
import com.oocl.service.DishManager;

@Controller
@RequestMapping("/api/cart")
public class CartController {
	@Resource(name="dishManagerImpl")
	private DishManager dishManager;

	@RequestMapping(value="all", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> findAllDishes(HttpSession sess) {
		Map<String, CartItem> cartItems = getCartItems(sess);
		return new JSONResponse<CartItem>(1, null, null, cartItems.values());
	}
	
	@RequestMapping(value="increase/{did}", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> increaseDish(@PathVariable String did, HttpSession sess) {
		CartItem item = getCartItems(sess).get(did);
		
		if (item == null) {
			Dish d = dishManager.findDishById(did);
			
			if (d == null) {
				return new JSONResponse<CartItem>(0, "Dish not exist!", null, null);
			}
			
			item = new CartItem(did, d.getdName(), 1, d.getdPrice(), d.getdImage());
			getCartItems(sess).put(did, item);
		} else {
			item.setCount(item.getCount() + 1);
		}
		
		return new JSONResponse<CartItem>(1, null, null, null);
	}
	
	@RequestMapping(value="decrease/{did}", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> decreaseDish(@PathVariable String did, HttpSession sess) {
		CartItem item = getCartItems(sess).get(did);
		if (item.getCount() > 0) {
			item.setCount(item.getCount() - 1);
			return new JSONResponse<CartItem>(1, did, null, null);
		}
		return new JSONResponse<CartItem>(0, "Dish count is zero, cannot decrease.", null, null);
	}
	
	@RequestMapping(value="delete/{did}", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> deleteDish(@PathVariable String did, HttpSession sess) {
		getCartItems(sess).remove(did);
		return new JSONResponse<CartItem>(1, null, null, null);
	}
	
	@RequestMapping(value="clear", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> clear(HttpSession sess) {
		getCartItems(sess).clear();
		return new JSONResponse<CartItem>(1, null, null, null);
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, CartItem> getCartItems(HttpSession sess) {
		Map<String, CartItem> cartItems = (Map<String, CartItem>) sess.getAttribute("cart");
		if (cartItems == null) {
			cartItems = new ConcurrentHashMap<>();
			sess.setAttribute("cart", cartItems);
		}
		return cartItems;
	}
}
