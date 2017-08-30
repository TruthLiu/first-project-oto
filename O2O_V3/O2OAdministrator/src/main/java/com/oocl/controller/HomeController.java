package com.oocl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sec/")
public class HomeController {
	
	@RequestMapping(value="o2o_home")
	public String getAdvers(){
		
		return "views/o2o_home";
	}
}
