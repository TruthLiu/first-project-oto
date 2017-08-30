package com.oocl.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;


public interface MerchantService {
	
	public JSONResponse<Merchant> regist(MultipartHttpServletRequest multiRequest);
	
	public JSONResponse<Merchant> login(String mAccount,String pwd);
	
	public JSONResponse<Merchant> getApplyMsg(String id);
	
	public JSONResponse<Merchant> applyStore(MultipartHttpServletRequest multiRequest,Merchant oldMerchant); 
}
