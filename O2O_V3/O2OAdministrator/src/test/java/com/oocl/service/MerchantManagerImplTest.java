package com.oocl.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.dao.MerchantDao;
import com.oocl.pojo.Merchant;

public class MerchantManagerImplTest {
	static private ApplicationContext context;
	private static MerchantManager manager;
	
	@BeforeClass
	public static void init(){
		context = new ClassPathXmlApplicationContext("xml-config/applicationContext.xml");
		manager = (MerchantManager) context.getBean(MerchantManager.class);
	}
	

	@Test
	public void testAdd() {
		Merchant merchant = new Merchant(null, "ttt", "BBBB", "nnn", null, "hhhh", 1, false, "yyyy");
		manager.add(merchant);
		Assert.assertTrue(merchant!=null);
		System.out.println(merchant.getId());
	}

	@Test
	public void testUpdate() {
		Merchant merchant = new Merchant("8a5e9d325dd5ac20015dd5ac24400000", "Lisaa", "ttt", "hhh", null, "jj", 1, false, "kkk");
		Merchant m = manager.update(merchant);
		assertTrue(m!=null);
	}

	@Test
	public void testDelete() {
		Merchant merchant = manager.delete("8a5e9d325dd5ac20015dd5ac24400000");
		assertTrue(merchant!=null);
	}

	@Test
	public void testFindAll() {
		List<Merchant> merchants = manager.findAll();
		for (Merchant merchant : merchants) {
			System.out.println(merchant);
		}
	}

	@Test
	public void testFindMerchantById() {
		Merchant merchant = manager.findMerchantById("8a5e9d325dd5ad69015dd5ad6cfa0000");
		assertTrue(merchant!=null);
		System.out.println(merchant);
	}

	@Test
	public void testGetStatusById() {
		int status = manager.getStatusById("8a5e9d325dd5ad69015dd5ad6cfa0000");
		assertTrue(status==1);
	}

	@Test
	public void testFindMerchantByStatus() {
		List<Merchant> merchants = manager.findMerchantByStatus(1);
		for (Merchant merchant : merchants) {
			System.out.println(merchant);
		}
	}

	@Test
	public void testFindMerchantByBan() {
		List<Merchant> merchants = manager.findMerchantByIsban(false);
		for (Merchant merchant : merchants) {
			System.out.println(merchant);
		}
	}

	@Test
	public void testUpdateStatus() {
		int n = manager.updateStatus("8a5e9d325dd5ad69015dd5ad6cfa0000", 0);
		assertTrue(n==1);
	}

	@Test
	public void testUpdateBan() {
		int n = manager.updateIsBan("8a5e9d325dd5ad69015dd5ad6cfa0000", true);
		assertTrue(n==1);
	}

}
