package com.oocl.service;

import java.util.List;
import com.oocl.base.BaseManager;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.MerchantApplyRecord;

public interface MerchantManager extends BaseManager<Merchant>{
	
	public Merchant findMerchantById(String id);
	
	public int getStatusById(String id);
	
	public List<Merchant> findMerchantByStatus(Integer status);
	
	public List<Merchant> findMerchantByIsban(Boolean isBan);
	
	public int updateStatus(String id, Integer status);
	
	public int updateIsBan(String id, Boolean isBan);
	
	public List<MerchantApplyRecord> findHistory(String id);

}
