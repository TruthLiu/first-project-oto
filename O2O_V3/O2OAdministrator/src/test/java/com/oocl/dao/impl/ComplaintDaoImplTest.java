package com.oocl.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.dao.ComplaintDao;
import com.oocl.pojo.Complaint;

public class ComplaintDaoImplTest {
	
	private static ApplicationContext context;
	private static ComplaintDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("xml-config/applicationContext.xml");
		dao = (ComplaintDao) context.getBean("complaintDao");
	}
	
	@Test
	public void testAdd(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = null;
		try {
//			time = sdf.parse("2017-08-01");
			time = sdf.parse("2017-08-03 11:23:12"); //9aff3f7d-cd54-45fd-a5a2-5a018817da6c
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Complaint complaint = new Complaint(null, UUID.randomUUID().toString(), "9aff3f7d-cd54-45fd-a5a2-5a018817da6c", "msg", time);
		Complaint result = dao.add(complaint);
		Assert.assertTrue(result!=null);
		System.out.println(result.getId()+"\tmid:"+result.getMid());
 	}
	
	@Test
	public void testFindByMid(){
		List<Complaint> complaints = dao.findByMid("264d5b58-ab82-45ed-822d-25a72b56dec7");
		Assert.assertTrue(complaints.size()==1);
	}
	
	@Test
	public void testFindByTime() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date from = sdf.parse("2017-08-01");
		Date to = sdf.parse("2017-08-10");
		List<Complaint> complaints = dao.findByTime("9aff3f7d-cd54-45fd-a5a2-5a018817da6c", from, to);
		Assert.assertTrue(complaints.size()==2);
	}

}
