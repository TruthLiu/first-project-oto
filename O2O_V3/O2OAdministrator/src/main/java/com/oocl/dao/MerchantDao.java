package com.oocl.dao;

import java.util.List;

import com.oocl.base.BaseDao;
import com.oocl.pojo.Merchant;

public interface MerchantDao extends BaseDao<Merchant>{
	
	
	public Merchant findMerchantById(String id);
	
	public int getStatusById(String id);
	
	public List<Merchant> findMerchantByStatus(Integer status);
	
	public List<Merchant> findMerchantByBan(Boolean isBan);
	
	public int updateStatus(String id, Integer status);
	
	public int updateBan(String id, Boolean Ban);

}
