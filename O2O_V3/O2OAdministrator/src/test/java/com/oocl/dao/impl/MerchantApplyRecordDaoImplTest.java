package com.oocl.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.dao.MerchantApplyRecordDao;
import com.oocl.dao.MerchantDao;
import com.oocl.pojo.MerchantApplyRecord;

public class MerchantApplyRecordDaoImplTest {
	static private MerchantApplyRecordDao recordDao;
	static private ApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new ClassPathXmlApplicationContext("xml-config/applicationContext.xml");
		recordDao = context.getBean(MerchantApplyRecordDao.class);
	}
	
	// 297e47945dd172f9015dd172fb580000 hhhh    yyyy     0   NULL    nnn    Lisa BBBB          0
	//

	@Test
	public void testAdd() {
		MerchantApplyRecord applyRecord = new MerchantApplyRecord(null, "297e47945dd172f9015dd172fb580000", "tttt", "hhh", "aaa", "eee", null, 2, false, "4444");
		recordDao.add(applyRecord);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByMerchantId() {
		List<MerchantApplyRecord> record = recordDao.findByMerchantId("297e47945dd172f9015dd172fb580000");
		System.out.println(record);
	}

}
