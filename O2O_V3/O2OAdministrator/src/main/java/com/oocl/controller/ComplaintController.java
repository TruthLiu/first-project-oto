package com.oocl.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.dto.ComplaintSearchCondition;
import com.oocl.dto.DTO;
import com.oocl.dto.Protocol;
import com.oocl.pojo.Complaint;
import com.oocl.service.ComplaintManager;

@Controller
@RequestMapping("/sec/complaint/")
public class ComplaintController {
	@Resource(name="complaintManager")
	private ComplaintManager manager;
	
	@RequestMapping("home")
	public String complaintHome(){
		
		return "views/complaint/complaint_search";
	}
	
	@ResponseBody
	@RequestMapping(value="search",method=RequestMethod.POST,consumes="application/json")
	public DTO<Complaint> findComplaintByCondition(@RequestBody ComplaintSearchCondition condition){
		System.out.println(condition);
		if(condition==null){
			return new DTO<Complaint>(null, null, false);
		}
		DTO<Complaint> dtos = new DTO<>();
		dtos.setSuccess(true);
		List<Complaint> coms = null;
		if(condition.isAll()){	//	查询所有
			coms = manager.findAll();
		}else if(null==condition.getStartTime()){	//根据商家id查询
			coms = manager.findByMid(condition.getMid());
		}else if(null==condition.getEndTime()){	//根据商家id和开始时间查询
			coms = manager.findByTime(condition.getMid(), condition.getStartTime(), new Date());
		}else {
			coms = manager.findByTime(condition.getMid(), condition.getStartTime(), condition.getEndTime());
		}
		dtos.setObjList(coms);
		return dtos;
	}
}	
