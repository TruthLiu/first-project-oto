package com.oocl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.DishDao;
import com.oocl.dao.DishTypeDao;
import com.oocl.pojo.Dish;
import com.oocl.service.DishService;

@Service(value="dishServiceImpl")
public class DishServiceImpl implements DishService{
	@Resource(name="dishDaoImpl")
	private DishDao dishDao;
	@Transactional
	@Override
	public Dish addDish(Dish d) {
		return dishDao.addDish(d);
	}
	@Transactional
	@Override
	public Dish updateDish(Dish d) {
		Dish dish = null;
		if(d.getId()!=null)
			dish= dishDao.updateDish(d);
		return dish;
	}
	@Transactional
	@Override
	public Dish deleteDish(String id) {
		Dish dish = null;
		if(null!=id)
			dish=dishDao.deleteDish(id);
		return dish;
	}
	public List<Dish> findAllDishes(String mId) {
		return dishDao.findAllDishes(mId);
	}

	@Override
	public List<Dish> findDishesByType(Integer dType, String mId) {
		return dishDao.findDishesByType(dType, mId);
	}

	@Override
	public List<Dish> findDishesByName(String dName, String mId) {
		return dishDao.findDishesByName(dName, mId);
	}

	@Override
	public List<Dish> findDishesByBoth(Integer dType, String dName, String mId) {
		return dishDao.findDishesByBoth(dType, dName, mId);
	}

	@Override
	public Dish findDishById(String dId) {
		return dishDao.findDishById(dId);
	}
}
