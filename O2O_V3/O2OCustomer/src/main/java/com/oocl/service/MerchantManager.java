package com.oocl.service;

import java.util.Collection;
import java.util.List;

import com.oocl.pojo.Merchant;

public interface MerchantManager {
	
	/**
	 * 根据商家名字搜索商家<br/> 
	 * 当存在符合条件的商家记录条时，返回值为商家对象，否则返回null<br/> 
	 * */
	public Merchant findOneMerchantByName(String mName);
	
	/**
	 * 根据商家ID搜索商家<br/> 
	 * 当存在符合条件的商家记录条时，返回值为商家对象，否则返回null<br/> 
	 * */
	public Merchant findOneMerchantById(String mId);
		
	/**
	 * 按条件搜索商家<br/> 
	 * 当不限制所含菜品时，可将参数dType设为0<br/> 
	 * 当不需要使用菜品名或商家名关键字搜索时，可将参数Name设为null<br/> 
	 * 返回值为符合条件的商家对象列表，当没有符合条件商家时列表长度=0<br/> 
	 * */
	public List<Merchant> findMerchantsByMIdList(String Name,Integer dType,Collection<String> mIdList);
	
	/**
	 * 输入一个商家ID列表<br/>
	 * 返回其中过审的商家的ID列表<br/>
	 * */
	public List<String> MerchantsIdFilterByStatus(Collection<String> mIdList);
	
	
	
	/**
	 * 更新ID为mId的商家的星级
	 * */
	public Merchant UpdateMerchantStar(String mId);
	
	/**
	 * 获得ID为mId的商家的星级
	 * */
	public Double getMerchantStar(String mId);
}
