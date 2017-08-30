package com.oocl.service;

import java.util.List;

import com.oocl.pojo.Dish;

public interface DishManager {

	/**
	 * 按条件搜索菜品<br/>
	 * 当要搜索全部种类的菜品时，可将参数dType设为0<br/>
	 * 当不需要使用菜品名搜索时，可将参数dName设为null<br/>
	 * 返回值为符合条件的菜品对象列表，当没有符合条件菜品时列表长度=0<br/>
	 * */
	public List<Dish> findDishes(Integer dType,String dName,String mId);
	
	/**
	 * 按菜品ID查找菜品<br/>
	 * 当存在符合条件的菜品记录条时，返回值为菜品对象，否则返回null<br/>
	 * */
	public Dish findDishById(String dId);
}
