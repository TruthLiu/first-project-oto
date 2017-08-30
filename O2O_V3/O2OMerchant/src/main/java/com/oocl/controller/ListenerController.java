package com.oocl.controller;

import javax.servlet.http.HttpSession;

import oracle.net.aso.s;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.memory.SystemMemory;
import com.oocl.pojo.Merchant;

@RequestMapping("/listen")
@Controller
public class ListenerController {
	private Logger logger = Logger.getLogger(ListenerController.class);


	@RequestMapping(value="/ban",method=RequestMethod.GET)
	public void listenBan(String id,Boolean isBan,HttpSession session){
		logger.info("ban---id:"+id+",isBan:"+isBan);
		if (isBan) {
			SystemMemory.addBanSet(id);
		}else {
			SystemMemory.removeBanSet(id);
		}
//		return "redirect:/listen/dealWithBan";
	}
	
//	@RequestMapping(value="/dealWithBan")
//	public void dealWithBan(String id,Boolean isBan,HttpSession session){
//		Merchant merchant = (Merchant)session.getAttribute("merchant");
//		if (merchant.getId().equals(id)) {
//			logger.info("id:"+id+",isBan:"+isBan);
//			session.setAttribute("isBan", isBan);
//		}
//	}
	
}
