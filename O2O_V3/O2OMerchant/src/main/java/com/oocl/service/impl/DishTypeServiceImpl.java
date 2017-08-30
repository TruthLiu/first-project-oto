package com.oocl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.DishTypeDao;
import com.oocl.pojo.DishType;
import com.oocl.service.DishTypeService;

@Service(value="dishTypeServiceImpl")
public class DishTypeServiceImpl implements DishTypeService{
	@Resource(name="dishTypeDaoImpl")
	private DishTypeDao dishTypeDao;
	@Transactional
	@Override
	public List<DishType> getAllDishTypes() {
		return dishTypeDao.getAllDishTypes();
	}

}
