package com.oocl.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.pojo.DishType;
import com.oocl.service.DishService;
import com.oocl.service.DishTypeService;

public class DishTypeServiceImplTest {
	private static ApplicationContext context;
	private DishTypeService ds;
	@BeforeClass
	public static void init(){
		context=new ClassPathXmlApplicationContext("xml-config/applicationContext.xml");
	}
	@Before
	public void setUp() throws Exception {
		ds=context.getBean(DishTypeService.class);
	}
	@Test
	public void testGetAllDishTypes() {
		List<DishType> dt=ds.getAllDishTypes();
		Assert.assertTrue(dt.isEmpty());
	}

}
