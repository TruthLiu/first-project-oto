package com.oocl.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
	
	private Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping("login")
	public String login(String name, String pwd, HttpSession session){
		if("admin".equals(name) && "admin".equals(pwd)){
			logger.info("administrator log in");
			session.setAttribute("adminName", name);
			session.setAttribute("adminPwd", pwd);
			return "redirect:sec/o2o_home";
		}else{
			return "redirect:html/login.html";	//Admin name or password is wrong!
		}
		
	}
	
	
	@RequestMapping("sec/signout")
	public String signOut(HttpSession session){
		logger.info("admin user sign out");
		session.invalidate();
		return "redirect:../html/login.html";
	}

}
