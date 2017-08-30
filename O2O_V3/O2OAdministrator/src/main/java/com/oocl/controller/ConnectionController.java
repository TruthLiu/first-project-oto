package com.oocl.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.dto.ComplaintSearchCondition;
import com.oocl.dto.DTO;
import com.oocl.dto.Protocol;
import com.oocl.dto.State;
import com.oocl.pojo.Advertisement;
import com.oocl.pojo.Complaint;
import com.oocl.pojo.Merchant;
import com.oocl.service.AdvertisementManager;
import com.oocl.service.ComplaintManager;
import com.oocl.service.MerchantManager;
import com.oocl.util.DESUtil;
import com.oocl.util.HttpConUtil;
import com.oocl.util.JSONUtil;

@Controller
@RequestMapping("/")
public class ConnectionController {
	@Resource(name="merchantManager")
	private MerchantManager manager;
	@Resource(name="complaintManager")
	private ComplaintManager complaintManager;
	@Resource(name="adManager")
	private AdvertisementManager adManager;
	
	private Logger logger = Logger.getLogger(ConnectionController.class);
	
	@RequestMapping(value="approvalResult", method=RequestMethod.GET)
	@ResponseBody
	public Protocol approvalResult(String id) throws UnsupportedEncodingException{
//		idSource = idSource.replaceAll(" ", "+");
//		DESUtil desUtil = new DESUtil();
//		String id = desUtil.decrypt(idSource,"UTF-8");
		if(id != null){
			Merchant merchant = manager.findMerchantById(id);
			int status=-1;
			if(merchant == null){
				status=-1;
			}else {
				status = merchant.getStatus();
			}
			State state = new State();
			state.setState(status);
			if(status==2){
				state.setContent(merchant.getbContent());
			}
			Protocol protocol = new Protocol();
			protocol.setFlag(true);
			protocol.setObject(state);
			logger.info("respon status: "+status);
			return protocol;
		} else {
			Protocol protocol = new Protocol();
			protocol.setFlag(false);
			logger.info("approval result without id");
			return protocol;
		}
	}
	
	@RequestMapping(value="getMerchant",method=RequestMethod.GET)
	@ResponseBody
	public Protocol getMerchants(String type) throws UnsupportedEncodingException{
		boolean isBan;
		if("black".equals(type)){
			isBan=true;
		}else{
			isBan=false;
		}
		List<Merchant> merchants = manager.findMerchantByIsban(isBan);
		if(!merchants.isEmpty()){
			Set<Object> merchantIdSet = new HashSet<Object>();
			for(Merchant merchant:merchants){
				String id = merchant.getId();
				merchantIdSet.add(id);
			}
			Protocol protocol = new Protocol();
			protocol.setFlag(true);
			protocol.setSet(merchantIdSet);
			logger.info("customer get merchant list");
			return protocol;
		} else {
			Protocol protocol = new Protocol();
			protocol.setFlag(false);
			return protocol;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="getComplaints",method=RequestMethod.POST,consumes="application/json")
	public Protocol<Complaint> findComplaintByCondition(@RequestBody ComplaintSearchCondition condition){
		System.out.println(condition);
		if(condition==null){
			return new Protocol<>(false, "search condition can not be null");
		}
		Protocol<Complaint> protocol=new Protocol<>();
		List<Complaint> coms = null;
		logger.info("customer get comlaint list");
		if(condition.isAll()){	//	查询所有
			coms = complaintManager.findAll();
		}else if(null==condition.getStartTime()){	//根据商家id查询
			coms = complaintManager.findByMid(condition.getMid());
		}else if(null==condition.getEndTime()){	//根据商家id和开始时间查询
			coms = complaintManager.findByTime(condition.getMid(), condition.getStartTime(), new Date());
		}else {
			coms = complaintManager.findByTime(condition.getMid(), condition.getStartTime(), condition.getEndTime());
		}
		protocol.setList(coms);
		return protocol;
	}
	
	//发送回执
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="getRecommendedAds")
	public Protocol<Advertisement> findAds(){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("recommend", 1);
		List<Advertisement> advers = adManager.findByCondition(condition);
		Protocol<Advertisement> protocol = new Protocol<>(true, advers);
		return protocol;
	}

}
