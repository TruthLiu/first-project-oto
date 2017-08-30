package com.oocl.dao;

import com.oocl.pojo.Merchant;

public interface MerchantDao {
	public String MerchantIdFilterByStatus(String mId);//
	public Merchant findOneMerchantByMId(String mId);//
	public Merchant findOneMerchantByDTypeAndMId(Integer dType,String mId);//
	public Merchant findOneMerchantByDNameAndMId(String Name,String mId);//
	public Merchant findOneMerchantByAllThree(String Name,Integer dType,String mId);//
	public Merchant findOneMerchantByMName(String mName);//
	public Merchant UpdateMerchantStar(String mId);//
	public Double getMerchantStar(String mId);
}
