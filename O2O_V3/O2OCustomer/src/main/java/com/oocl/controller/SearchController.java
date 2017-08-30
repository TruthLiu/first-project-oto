package com.oocl.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.constant.MerchantStatus;
import com.oocl.pojo.Dish;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.SearchParams;
import com.oocl.service.DishManager;
import com.oocl.service.MerchantManager;
import com.oocl.util.HttpConnectionUtil;
import com.oocl.util.IPPropertiesUtil;
import com.oocl.util.StringUtil;

@Controller
@RequestMapping("/api/search")
public class SearchController {
	
	private final String GET_MERCHANTS_URL = String.format(
			IPPropertiesUtil.getProperty("admin.getmerchants.url"),
			IPPropertiesUtil.getProperty("admin.ipaddr"),
			IPPropertiesUtil.getProperty("admin.port"));
	
	@Resource(name="merchantManagerImpl")
	private MerchantManager merchantManager;
	
	@Resource(name="dishManagerImpl")
	private DishManager dishManager;

	@RequestMapping(value="restaurant", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> searchRestaurants(@RequestBody SearchParams params) {
		String merchantsJson = HttpConnectionUtil.sendGet(GET_MERCHANTS_URL);
		
		if (StringUtil.isNullOrEmpty(merchantsJson)) {
			return new JSONResponse<Merchant>(0, "Error retrieving restaurant list!", null, null);
		}
		
		Set<String> listObj = HttpConnectionUtil.getASideResponse(merchantsJson).getSet();
		List<String> allAllowedMids = merchantManager.MerchantsIdFilterByStatus(listObj);
		
		List<Merchant> merchantsList = merchantManager.findMerchantsByMIdList(params.getKeyword(), params.getDtype(), allAllowedMids);
		
		return new JSONResponse<Merchant>(1, null, null, merchantsList);
	}
	
	@RequestMapping(value="dish", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> searchDishes(@RequestBody SearchParams params) {
//		String merchantsJson = HttpConnectionUtil.sendGet(GET_MERCHANTS_URL);
//		
//		if (StringUtil.isNullOrEmpty(merchantsJson)) {
//			return new JSONResponse<Merchant>(0, "Error retrieving restaurant list!", null, null);
//		}
//		
//		Set<String> listObj = HttpConnectionUtil.getASideResponse(merchantsJson).getSet();
//		List<String> allAllowedMids = merchantManager.MerchantsIdFilterByStatus(listObj);
//		
//		Merchant m = merchantManager.findOneMerchantById(params.getMid());
//		if (m == null || !m.getStatus().equals(MerchantStatus.APPLY_PASSED) || !allAllowedMids.contains(m.getId())) {
//			return new JSONResponse<Merchant>(0, "The restaurant is not exist/not passed/on blacklist!", null, null);
//		}
		
		List<Dish> dishList = dishManager.findDishes(params.getDtype(), params.getKeyword(), params.getMid());
		return new JSONResponse<Dish>(1, null, null, dishList);
	}
}
