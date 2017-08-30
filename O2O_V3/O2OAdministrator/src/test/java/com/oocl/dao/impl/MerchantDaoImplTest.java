package com.oocl.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.dao.MerchantDao;
import com.oocl.pojo.Merchant;

public class MerchantDaoImplTest {
	static private MerchantDao merchantDao;
	static private ApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new ClassPathXmlApplicationContext("xml-config/applicationContext.xml");
		merchantDao = context.getBean(MerchantDao.class);
	}
	

	@Test
	public void testAdd() {
		Merchant merchant = new Merchant(null, "ttt", "BBBB", "nnn", null, "hhhh", 1, false, "yyyy");
		merchantDao.add(merchant);
	}

	@Test
	public void testUpdate() {
		Merchant merchant = new Merchant("297e47945dd16d49015dd16d4bd60000", "Lisaa", "ttt", "hhh", null, "jj", 1, false, "kkk");
		Merchant m = merchantDao.update(merchant);
		assertTrue(m!=null);
	}

	@Test
	public void testDelete() {
		Merchant merchant = merchantDao.delete("297e47945dd16d49015dd16d4bd60000");
		assertTrue(merchant!=null);
	}

	@Test
	public void testFindAll() {
		List<Merchant> merchants = merchantDao.findAll();
		for (Merchant merchant : merchants) {
			System.out.println(merchant);
		}
	}

	@Test
	public void testFindMerchantById() {
		Merchant merchant = merchantDao.findMerchantById("297e47945dd16d49015dd16d4bd60000");
		assertTrue(merchant!=null);
		System.out.println(merchant);
	}

	@Test
	public void testGetStatusById() {
		int status = merchantDao.getStatusById("297e47945dd172f9015dd172fb580000");
		assertTrue(status==2);
	}

	@Test
	public void testFindMerchantByStatus() {
		List<Merchant> merchants = merchantDao.findMerchantByStatus(2);
		for (Merchant merchant : merchants) {
			System.out.println(merchant);
		}
	}

	@Test
	public void testFindMerchantByBan() {
		List<Merchant> merchants = merchantDao.findMerchantByBan(true);
		for (Merchant merchant : merchants) {
			System.out.println(merchant);
		}
	}

	@Test
	public void testUpdateStatus() {
		int n = merchantDao.updateStatus("297e47945dd172f9015dd172fb580000", 0);
		assertTrue(n==1);
	}

	@Test
	public void testUpdateBan() {
		int n = merchantDao.updateBan("297e47945dd172f9015dd172fb580000", false);
		assertTrue(n==1);
	}

}
