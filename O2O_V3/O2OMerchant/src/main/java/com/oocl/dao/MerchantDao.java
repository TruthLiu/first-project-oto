package com.oocl.dao;

import com.oocl.pojo.Merchant;

public interface MerchantDao {
	public Merchant addMerchant(Merchant m);
	
	public Merchant updateMerchant(Merchant m);
	
	public Merchant findMerchantById(String id);
	
	public Merchant findMerchantByName(String mName);
	
	public Merchant findMerchantByAccount(String mAccount);
	
}
