package com.oocl.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oocl.util.IPPropertiesUtil;

@Controller
public class ResourcesController {

	private final String MERCHANT_SERVER_URL = String.format(
			IPPropertiesUtil.getProperty("fileserver.res.url"),
			IPPropertiesUtil.getProperty("fileserver.ipaddr"),
			IPPropertiesUtil.getProperty("fileserver.port"));

	@RequestMapping(value="/res/**", method=RequestMethod.GET)
	public String getResource(HttpServletRequest request) throws IOException {
		String requestUri = request.getRequestURI();
		String regex = "^\\" + request.getContextPath() + "\\/res(\\/.*)";
		Matcher matcher = Pattern.compile(regex).matcher(requestUri);
		matcher.matches();
		
		String remoteUrl = MERCHANT_SERVER_URL + matcher.group(1);
		return "redirect:" + remoteUrl;
	}
}
