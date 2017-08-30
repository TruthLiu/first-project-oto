package com.oocl.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.dao.AdvertisementDao;
import com.oocl.pojo.Advertisement;

public class AdvertisementManagerImplTest {
	private static ApplicationContext context;
	private static AdvertisementManager manager;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("xml-config/applicationContext.xml");
		manager = (AdvertisementManager) context.getBean("adManager");
	}
	
	@Test
	public void testAdd(){
		Advertisement ad = new Advertisement(null, UUID.randomUUID().toString(), UUID.randomUUID().toString(), "img_path", new Date(), 1000, 1, 1);
		Advertisement result = manager.add(ad);
		Assert.assertTrue(result!=null);
		System.out.println(result.toString());
	}
	
	@Test
	public void testUpdateStatus(){
		Advertisement ad = manager.updateStatus("8a5e9d325dd96766015dd96769d60000", 0);
		Assert.assertTrue(ad.getStatus()==0);
	}
	
	@Test
	public void testUpdateRecommend(){
		Advertisement ad = manager.updateRecommend("8a5e9d325dd96766015dd96769d60000", 0);
		Assert.assertTrue(ad.getRecommend()==0);
	}
	
	@Test
	public void testFindAll(){
		List<Advertisement> advertisements = manager.findAll();
		Assert.assertTrue(advertisements.size()==2);
	}
	
	@Test
	public void testFindByRecommend(){
		List<Advertisement> advertisements = manager.findByRecommend(0);
		Assert.assertTrue(advertisements.size()==1);
	}
	
	@Test
	public void testFindByStatus(){
		List<Advertisement> advertisements = manager.findByStatus(0);
		Assert.assertTrue(advertisements.size()==1);
	}
}
