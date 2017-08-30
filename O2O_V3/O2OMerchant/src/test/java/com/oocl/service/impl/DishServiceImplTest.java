package com.oocl.service.impl;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.pojo.Dish;
import com.oocl.service.DishService;
public class DishServiceImplTest {
	private static ApplicationContext context;
	private DishService ds;
	@BeforeClass
	public static void init(){
		context=new ClassPathXmlApplicationContext("xml-config/applicationContext.xml");
	}
	@Before
	public void setUp() throws Exception {
		ds=context.getBean(DishService.class);
	}

	@Test
	public void testAddDish() {
		Dish dish=new Dish(UUID.randomUUID().toString(),"猪脚饭",34.52,"dImage",3,"mId");
		Dish d=ds.addDish(dish);
		Assert.assertTrue(d!=null);
	}

	@Test
	public void testUpdateDish() {// e0e4c02b-346f-4828-b8ef-a2a001e4c8a2 
		Dish dish=new Dish("e0e4c02b-346f-4828-b8ef-a2a001e4c8a2","鸡公煲",39.52,"d",3,"m");
		Dish dish2=ds.updateDish(dish);
		Assert.assertTrue(dish2!=null);
	}

	@Test
	public void testDeleteDish() {
		String id="e0e4c02b-346f-4828-b8ef-a2a001e4c8a2";
		Dish dish2=ds.deleteDish(id);
		Assert.assertTrue(dish2!=null);
	}

	@Test
	public void testFindAllDishes() {
		String mId="mId";
		List<Dish> d= ds.findAllDishes(mId);
		Assert.assertTrue(!d.isEmpty());
	}

	@Test
	public void testFindDishesByType() {
		 Integer dType=3;
		 String mId="mId";
		 List<Dish> d= ds.findDishesByType(dType, mId);
		 Assert.assertTrue(!d.isEmpty());
		 }

	@Test
	public void testFindDishesByName() {
		String dName="鸡公煲";
		String mId="m";
		List<Dish> d=ds.findDishesByName(dName, mId);
		Assert.assertTrue(!d.isEmpty());
	}

	@Test
	public void testFindDishesByBoth() {
		Integer dType=3;
		String dName="鸡公煲";
		String mId="m";
		List<Dish> d=ds.findDishesByBoth(dType, dName, mId);
		Assert.assertTrue(!d.isEmpty());
	}

	@Test
	public void testFindDishById() {
		String dId="e0e4c02b-346f-4828-b8ef-a2a001e4c8a2";
		Dish d=ds.findDishById(dId);
		System.out.println(d.getdName());
		Assert.assertTrue(d!=null);
	}

}
