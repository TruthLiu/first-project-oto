package com.oocl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.dto.Complaint;
import com.oocl.dto.ComplaintSearchCondition;
import com.oocl.dto.SearchCondition;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Protocol;
import com.oocl.util.CommonURL;
import com.oocl.util.HTTPConnUtil;
import com.oocl.util.JSONUtil;
import com.oocl.util.StatusCode;

@RequestMapping("/complaint")
@Controller
public class ComplaintController {
	private Logger logger = Logger.getLogger(MerchantController.class);
	
	@RequestMapping("sec/complaintPage")
	public String complaintPage(){
		logger.info("to complaint page...");
		return "complaint";
	}
	
	@RequestMapping("sec/getComplaints")
	@ResponseBody
	public JSONResponse<Complaint> getComplaints(@RequestBody SearchCondition searchCondition, HttpSession session){
		JSONResponse<Complaint> response = new JSONResponse<Complaint>();
		ComplaintSearchCondition complaintSearchCondition = new ComplaintSearchCondition();
		String mid = ((Merchant)session.getAttribute("merchant")).getId();
		complaintSearchCondition.setMid(mid);
		logger.info(mid+"\t"+searchCondition);
		if(searchCondition.getFrom()!=null){
				complaintSearchCondition.setStartTime(searchCondition.getFrom());
		}
		if(searchCondition.getTo()!=null){
				complaintSearchCondition.setEndTime(searchCondition.getTo());
		}
		JSONObject responseObject = HTTPConnUtil.sendPost(CommonURL.GET_COMPLAINT, complaintSearchCondition);
		JSONArray array = (JSONArray) responseObject.get("list");
		List<Complaint> result = new ArrayList<Complaint>();
		for (Object object : array) {
			JSONObject jsonObject = (JSONObject) object;
			System.out.println(jsonObject.toString());
			Complaint complaint = JSONUtil.toComplaint(jsonObject.toString());
			result.add(complaint);
		}
		
		response.setResultList(result);
		response.setCode(StatusCode.SUCCESS);
		return response;
	}
	

}
