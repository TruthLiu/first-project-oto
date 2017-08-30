package com.oocl.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oocl.pojo.Customer;
import com.oocl.service.CustomerManager;

@Controller
public class UserController {
	@Resource(name="customerManagerImpl")
	private CustomerManager customerManager;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	public String showRegisterPage() {
		return "register";
	}
	
	@RequestMapping(value="api/login", method=RequestMethod.POST)
	public String doLogin(String cname, String cpwd, RedirectAttributes redirectAttributes, HttpSession sess) {
		Customer c = customerManager.findCustomerByNameAndPwd(cname, cpwd);
		
		if (c == null) {
			redirectAttributes.addFlashAttribute("errorMsg", "Name or Password Incorrect!");
			return "redirect:/login";
		}
		
		sess.setAttribute("loginCustomer", c);
		return "redirect:/";
	}
	
	@RequestMapping(value="api/register", method=RequestMethod.POST)
	public String doRegister(String cname, String cpwd, RedirectAttributes redirectAttributes, HttpSession sess) {
		Customer c = customerManager.addCustomer(new Customer(UUID.randomUUID().toString(), cname, cpwd, null, null));
		
		if (c == null) {
			redirectAttributes.addFlashAttribute("errorMsg", "Register Failed!");
			return "redirect:/register";
		}
		sess.setAttribute("loginCustomer", c);
		return "redirect:/";
	}
	
	@RequestMapping(value="api/logout", method=RequestMethod.POST)
	public String doLogout(HttpSession sess) {
		sess.invalidate();
		return "redirect:/";
	}
}
