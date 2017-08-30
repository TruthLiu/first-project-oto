package com.oocl.service;

import java.util.List;
import java.util.Map;

import com.oocl.dto.QueryParam;
import com.oocl.pojo.Advertisement;

public interface AdvertisementManager {
	public Advertisement add(Advertisement advertisement);
	
	public Advertisement delete(Advertisement advertisement);
	
	public Advertisement updateStatus(String id, int status);
	
	public Advertisement updateRecommend(String id, int recommend);
	
	public List<Advertisement> findByRecommend(int recommend);
	
	public List<Advertisement> findAll();
	
	public List<Advertisement> findByStatus(int status);
	
	public Advertisement findByMid(String mid);
	
	public List<Advertisement> findByCondition(Map<String, Object> condition);
	
	public List<Advertisement> findByCondition(QueryParam param);

}
