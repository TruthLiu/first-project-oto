package com.oocl.service.impl;

import java.util.Map;

import com.oocl.pojo.CartItem;
import com.oocl.service.CartManager;

public class CartManagerImpl implements CartManager {

	@Override
	public boolean addCartItem(Map<String, CartItem> cart, CartItem ci) {
		String dId = ci.getdId();
		if(cart.containsKey(dId)){
			cart.get(dId).addCount();
		}
		else{
			cart.put(dId, ci);
			cart.get(dId).addCount();
		}
		return true;
	}

	@Override
	public boolean addCartItemCount(Map<String, CartItem> cart, String dId) {
		if(cart.containsKey(dId)){
			cart.get(dId).addCount();
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean reduceCartItemCount(Map<String, CartItem> cart, String dId) {
		if(cart.containsKey(dId)){
			cart.get(dId).reduceCount();
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Double TotalPrice(Map<String, CartItem> cart) {
		double tp = 0.0;
		for(String dId:cart.keySet()){
			tp+=cart.get(dId).totalPrice();
		}
		return tp;
	}

	@Override
	public void clearCart(Map<String, CartItem> cart) {
		cart.clear();
		
	}

	@Override
	public boolean deleteCartItem(Map<String, CartItem> cart, String dId) {
		if(cart.containsKey(dId)){
			cart.remove(dId);
			return true;
		}
		else{
			return false;
		}
	}
	
}
