package com.oocl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.oocl.dao.DishTypeDao;
import com.oocl.pojo.DishType;


@Repository(value = "dishTypeDaoImpl")
public class DishTypeDaoImpl implements DishTypeDao {


	@PersistenceContext(name="punit")
	private EntityManager entitymanager;
	
	@Override
	public List<DishType> getAllDishTypes() {
		String jpql="select dt from DishType dt";
		List<DishType> dishTypeList = entitymanager.createQuery(jpql)
				.getResultList();
		return dishTypeList;
	}

}
