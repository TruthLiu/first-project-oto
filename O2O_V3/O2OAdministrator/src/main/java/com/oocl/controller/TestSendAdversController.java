package com.oocl.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.dto.Protocol;

@Controller
public class TestSendAdversController {
	
	@RequestMapping(value="/getAdvers",method=RequestMethod.POST)
	@ResponseBody
	public Protocol getAdvers(String protocol,HttpServletRequest request) throws IOException{
//		InputStream in = request.getInputStream();
//		byte[] buf = new byte[in.available()];
//		in.read(buf);
		System.out.println("6666");
//		System.out.println(new String(buf));
		System.out.println(protocol);
//		in.close();
		return new Protocol<>(true, "");
	}
	
	@RequestMapping(value="/o2o_home")
	public String getAdvers(){
		
		return "o2o_home";
	}
	
	@RequestMapping(value="/two")
	public String getAdvers2(){
		
		return "two";
	}
}
