package com.oocl.service;

import java.util.List;

import com.oocl.pojo.Dish;

public interface DishService {
	public Dish addDish(Dish d);
	
	public Dish updateDish(Dish d);
	
	public Dish deleteDish(String id);
	
	public List<Dish> findAllDishes(String mId);
	
	public List<Dish> findDishesByType(Integer dType,String mId);
	
	public List<Dish> findDishesByName(String dName,String mId);
	
	public List<Dish> findDishesByBoth(Integer dType,String dName,String mId);
	
	public Dish findDishById(String dId);
}
