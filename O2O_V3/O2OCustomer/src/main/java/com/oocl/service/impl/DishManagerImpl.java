package com.oocl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oocl.dao.DishDao;
import com.oocl.dao.DishTypeDao;
import com.oocl.pojo.Dish;
import com.oocl.service.DishManager;


@Service("dishManagerImpl")
public class DishManagerImpl implements DishManager {

	@Resource(name = "dishDaoImpl")
	private DishDao dishDao;
	
	
	@Override
	public List<Dish> findDishes(Integer dType, String dName, String mId) {
		List<Dish> dl = null;
		if(dType==0){
			if(dName==null)dl=dishDao.findAllDishes(mId);
			else dl=dishDao.findDishesByName(dName, mId);
		}
		else {
			if(dName==null)dl=dishDao.findDishesByType(dType, mId);
			else dl=dishDao.findDishesByBoth(dType, dName, mId);
		}
		return dl;
	}


	@Override
	public Dish findDishById(String dId) {
		return dishDao.findDishById(dId);
	}

	

}
