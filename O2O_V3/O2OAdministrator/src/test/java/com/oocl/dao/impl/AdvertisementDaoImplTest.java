package com.oocl.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.dao.AdvertisementDao;
import com.oocl.dto.OrderBy;
import com.oocl.dto.QueryParam;
import com.oocl.pojo.Advertisement;

public class AdvertisementDaoImplTest {
	private static ApplicationContext context;
	private static AdvertisementDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("xml-config/applicationContext.xml");
		dao = (AdvertisementDao) context.getBean("adDao");
	}
	
	@Test
	public void testAdd(){
		Advertisement ad = new Advertisement(null, UUID.randomUUID().toString(), UUID.randomUUID().toString(), "img_path", new Date(), 777.3, 1, 1);
		Advertisement result = dao.add(ad);
		Assert.assertTrue(result!=null);
		System.out.println(result.toString());
	}
	
	@Test
	public void testUpdateStatus(){
		Advertisement ad = dao.updateStatus("78e493f3-5e5d-4f9b-9577-7dfbf3f1601b", 1);
		Assert.assertTrue(ad.getStatus()==0);
	}
	
	@Test
	public void testUpdateRecommend(){
		Advertisement ad = dao.updateRecommend("8a5e9d325dd19fd8015dd1a0127f0000", 0);
		Assert.assertTrue(ad.getRecommend()==0);
	}
	
	@Test
	public void testFindAll(){
		List<Advertisement> advertisements = dao.findAll();
		Assert.assertTrue(advertisements.size()==2);
	}
	
	@Test
	public void testFindByRecommend(){
		List<Advertisement> advertisements = dao.findByRecommend(0);
		Assert.assertTrue(advertisements.size()==1);
	}
	
	@Test
	public void testFindByStatus(){
		List<Advertisement> advertisements = dao.findByStatus(0);
		Assert.assertTrue(advertisements.size()==1);
	}
	
	@Test
	public void testFindByCondition1(){
		QueryParam param = new QueryParam();
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("mid", "78e493f3-5e5d-4f9b-9577-7dfbf3f1601b");
//		map.put("status", 0);
//		map.put("recommend", 0);
//		param.setAndParams(map);
		
		OrderBy o1 = new OrderBy();
		o1.setAsc(true);
		o1.setField("price");
		List<OrderBy> orders = new ArrayList<>();
		orders.add(o1);
		param.setOrderParams(orders);
		param.setStartIndex(5);
		param.setMaxCounts(3);
		List<Advertisement> ads = dao.findByCondition(param);
		for (Advertisement advertisement : ads) {
			System.out.println(advertisement);
		}
	}
	
	@Test
	public void testFindByCondition(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mid", "78e493f3-5e5d-4f9b-9577-7dfbf3f1601b");//78e493f3-5e5d-4f9b-9577-7dfbf3f1601b
//		map.put("status", 0);
//		map.put("recommend", 1);
		List<Advertisement> ads = dao.findByCondition(map);
		for (Advertisement advertisement : ads) {
			System.out.println(advertisement);
		}
	}
}
