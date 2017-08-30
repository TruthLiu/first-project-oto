package com.oocl.service;

import java.util.List;

import com.oocl.pojo.DishType;

public interface DishTypeService {
	/**
	 * 返回所有菜品类型
	 * */
	public List<DishType> getAllDishTypes();
}
