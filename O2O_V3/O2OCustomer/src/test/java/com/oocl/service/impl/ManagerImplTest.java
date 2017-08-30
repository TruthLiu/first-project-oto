package com.oocl.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.pojo.Comment;
import com.oocl.pojo.Complaint;
import com.oocl.pojo.Customer;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Order;
import com.oocl.pojo.OrderItem;
import com.oocl.pojo.Reply;
import com.oocl.service.CommentManager;
import com.oocl.service.ComplaintManager;
import com.oocl.service.MerchantManager;
import com.oocl.service.OrderManager;
import com.oocl.vo.OrderVO;

public class ManagerImplTest {

	private static ApplicationContext context;
	private static MerchantManager merchantManager;
	private static OrderManager orderManager;
	private static CommentManager commentManager;
	private static ComplaintManager complaintManager;
	
	@BeforeClass
	public static void init(){
		context = new ClassPathXmlApplicationContext("classpath:xml-config/applicationContext.xml");
		merchantManager = (MerchantManager) context.getBean("merchantManagerImpl");
		orderManager = (OrderManager)context.getBean("orderManagerImpl");
		commentManager = (CommentManager)context.getBean("commentManagerImpl");
		complaintManager = (ComplaintManager)context.getBean("complaintManagerImpl");
		
	}

	
	@Test
	public void test1() throws JsonProcessingException {
		OrderVO orderVO= orderManager.findDetailedOrder("c1523069-1d58-48ba-99fa-060ad60c0503");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(orderVO));
	}

	@Test
	public void test2() throws JsonProcessingException {
		List<OrderVO> orderVOs = orderManager.findGeneralOrderList("cid1");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(orderVOs));
	}
	
	
	@Test
	public void test3() throws JsonProcessingException {
		Order order1 = new Order();
		order1.setId(UUID.randomUUID().toString());
		order1.setAddr("oaddr333");
		order1.setRealName("orn3333");
		order1.setPhone("ophone3333");
		order1.setCreateTime(Calendar.getInstance().getTime());
		order1.setStatus(0);
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setId(UUID.randomUUID().toString());
		orderItem1.setDishName("beef333");
		orderItem1.setUnitPrice(5.5);
		orderItem1.setNum(6);
		orderItem1.setOrder(order1);
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setId(UUID.randomUUID().toString());
		orderItem2.setDishName("tofu333");
		orderItem2.setUnitPrice(2.0);
		orderItem2.setNum(4);
		orderItem2.setOrder(order1);
		order1.getItems().add(orderItem1);
		order1.getItems().add(orderItem2);
		System.out.println(orderManager.addOrder(order1, "cid1", "mid1").getId());
	}

	@Test
	public void test4() throws JsonProcessingException {
		Comment comment = new Comment("cm1", 1.0, "难吃", null, null, null, null,new Date());
//		commentManager.addComment(comment, "c1523069-1d58-48ba-99fa-060ad60c0503");	
		Assert.assertTrue(commentManager.addComment(comment, "c1523069-1d58-48ba-99fa-060ad60c0503")==null);
	}
	
	@Test
	public void test5() throws JsonProcessingException {
		Complaint complaint = new Complaint("cmpl1", "难吃 服务态度差", new Date(), null, null, null);
//		complaintManager.addComplaint(complaint, "c1523069-1d58-48ba-99fa-060ad60c0503");
		Assert.assertTrue(complaintManager.addComplaint(complaint, "c1523069-1d58-48ba-99fa-060ad60c0503")==null);
	}
		
}
