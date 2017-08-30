package com.oocl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.dto.DTO;
import com.oocl.dto.Protocol;
import com.oocl.dto.QueryParam;
import com.oocl.pojo.Advertisement;
import com.oocl.service.AdvertisementManager;
import com.oocl.util.HttpConUtil;

@Controller
@RequestMapping("/sec/adver/")
public class AdvertismentController {
	@Resource(name="adManager")
	private AdvertisementManager manager;
	
	@RequestMapping(value="search")
	public String toSearchPage(){
		
		return "views/adver/search";
	}
	
	@RequestMapping(value="process")
	public String topProcessage(){
		
		return "views/adver/process";
	}
	
	//多条件查询
	@ResponseBody
	@RequestMapping(value="search",method=RequestMethod.POST,consumes="application/json")
	public DTO<Advertisement> findComplaintByCondition(@RequestBody Map<String, Object> conditions){
		if(null==conditions){
			return new DTO<Advertisement>(null, null, false);
		}
		DTO<Advertisement> dtos = new DTO<>();
		dtos.setSuccess(true);
		List<Advertisement> advers=null;
		if(conditions.isEmpty()){
			advers = manager.findByCondition(new HashMap<String, Object>());
		}else {
			advers = manager.findByCondition(conditions);
		}
		dtos.setObjList(advers);
		return dtos;
	}
	
	//审核查询
	@ResponseBody
	@RequestMapping(value="findCount",method=RequestMethod.POST,consumes="application/json")
	public DTO<Advertisement> findComplaintByCondition(@RequestBody QueryParam queryParam){
		System.out.println(queryParam);
		if(null==queryParam){
			return new DTO<Advertisement>(null, null, false);
		}
		DTO<Advertisement> dtos = new DTO<>();
		dtos.setSuccess(true);
		List<Advertisement> advers=manager.findByCondition(queryParam);
		dtos.setObjList(advers);
		return dtos;
	}
	
	//修改status
	@ResponseBody
	@RequestMapping("/updateStatus/{aid}/{status}")
	public DTO<String> updateStatus(@PathVariable String aid,@PathVariable int status){
		try {
			DTO<String> dto = new DTO<>();
			Advertisement adver = manager.updateStatus(aid, status);
			if(null!=adver){
				dto.setObj("update Success");
				dto.setSuccess(true);
				return dto;
			}else {
				return new DTO<String>("update fail", null, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new DTO<String>("update fail", null, false);
		}
	}
	
	//修改是否推荐
	@ResponseBody
	@RequestMapping("/updateRecommend/{aid}/{recommend}")
	public DTO<String> updateRecommend(@PathVariable String aid,@PathVariable int recommend){
		try {
			DTO<String> dto = new DTO<>();
			Advertisement adver = manager.updateRecommend(aid, recommend);
			if(null!=adver){
				dto.setObj("update Success");
				dto.setSuccess(true);
				return dto;
			}else {
				return new DTO<String>("update fail", null, false);
			}
		} catch (Exception e) {
			return new DTO<String>("update fail", null, false);
		}
	}
	
	//发送回执
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="getRecommendedAds")
	public DTO<String> findAds(){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("status", 1);
		condition.put("recommend", 1);
		List<Advertisement> advers = manager.findByCondition(condition);
		Protocol<Advertisement> protocol = new Protocol<>(true, advers);
		Protocol rs = HttpConUtil.sendPost("http://localhost:8080/O2OAdministrator/getAdvers", protocol);
		if(rs.isFlag()){
			for(Advertisement a:advers){
				manager.updateStatus(a.getId(), 2);
			}
			return new DTO<String>("Send Success", null, true);
		}else {
			return new DTO<String>("Send fail", null, false);
		}
	}
}
