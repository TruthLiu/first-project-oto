package com.oocl.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.pojo.Merchant;
import com.oocl.service.MerchantService;

public class MerchantServiceImplTest {
	private static ApplicationContext context;
	private static MerchantService merchantService;
	
	@BeforeClass
	public static void init(){
		context = new ClassPathXmlApplicationContext("classpath:xml-config/applicationContext.xml");
		merchantService = (MerchantService) context.getBean("merchantServiceImpl");
	}

	@Test
	public void testLogin() {
//		Merchant merchant = merchantService.login("oscar", "123");
	}

}
