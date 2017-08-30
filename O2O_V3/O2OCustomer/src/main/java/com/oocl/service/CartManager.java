package com.oocl.service;

import java.util.Map;

import com.oocl.pojo.CartItem;

public interface CartManager {
	public boolean addCartItem(Map<String, CartItem> cart,CartItem ci);
	public boolean deleteCartItem(Map<String, CartItem> cart,String dId);
	public boolean addCartItemCount(Map<String, CartItem> cart,String dId);
	public boolean reduceCartItemCount(Map<String, CartItem> cart,String dId);
	public Double TotalPrice(Map<String, CartItem> cart);
	public void clearCart(Map<String, CartItem> cart);
}
