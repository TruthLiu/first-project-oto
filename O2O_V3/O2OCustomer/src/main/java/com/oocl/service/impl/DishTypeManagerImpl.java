package com.oocl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oocl.dao.DishTypeDao;
import com.oocl.dao.MerchantDao;
import com.oocl.pojo.DishType;
import com.oocl.service.DishTypeManager;


@Service("dishTypeManagerImpl")
public class DishTypeManagerImpl implements DishTypeManager{

	@Resource(name = "dishTypeDaoImpl")
	private DishTypeDao dishTypeDao;
	
	@Override
	public List<DishType> getAllDishTypes() {
		return dishTypeDao.getAllDishTypes();
	}
	
}
