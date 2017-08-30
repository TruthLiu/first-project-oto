package com.oocl.service.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.pojo.MenuItem;
import com.oocl.service.MenuItemService;
import com.oocl.service.MerchantService;

public class MenuItemServiceImplTest {
	private static ApplicationContext context;
	private static MenuItemService menuItemService;
	
	@BeforeClass
	public static void init(){
		context = new ClassPathXmlApplicationContext("classpath:xml-config/applicationContext.xml");
		menuItemService = (MenuItemService) context.getBean("menuItemServiceImpl");
	}
	@Test
	public void testAddMenuItem() {
		MenuItem menuItem1 = new MenuItem("Merchant", "merchant/sec/message", "file/img/merchant.png", null);
		MenuItem menuItem2 = new MenuItem("Dish", "dish/sec/dishManage", "file/img/dish.png", null);
		MenuItem menuItem3 = new MenuItem("Order", "order/sec/message", "file/img/order.png", null);
		MenuItem menuItem4 = new MenuItem("Comment", "comment/sec/commentPage", "file/img/comment.png", null);
		menuItemService.addMenuItem(menuItem1);
		menuItemService.addMenuItem(menuItem2);
		menuItemService.addMenuItem(menuItem3);
		menuItemService.addMenuItem(menuItem4);
	}

	@Test
	public void testFindAllMenuItem() {
		List<MenuItem> list = menuItemService.findAllMenuItem();
		Assert.assertTrue(list.size()==4);
	}

}
