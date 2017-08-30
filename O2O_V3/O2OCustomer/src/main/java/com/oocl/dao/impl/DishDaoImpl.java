package com.oocl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.oocl.dao.DishDao;
import com.oocl.pojo.Dish;


@Repository(value = "dishDaoImpl")
public class DishDaoImpl implements DishDao{

	@PersistenceContext(name="punit")
	private EntityManager entitymanager;
	
	@Override
	public List<Dish> findAllDishes(String mId) {
		String jpql="select d from Dish d where d.mId=:mId";
		List<Dish> dishList = entitymanager.createQuery(jpql)
				.setParameter("mId", mId)
				.getResultList();
		return dishList;
	}

	@Override
	public List<Dish> findDishesByType(Integer dType, String mId) {
		String jpql="select d from Dish d where d.mId=:mId and d.dType=:dType";
		List<Dish> dishList = entitymanager.createQuery(jpql)
				.setParameter("mId", mId)
				.setParameter("dType", dType)
				.getResultList();
		return dishList;
	}

	@Override
	public List<Dish> findDishesByName(String dName, String mId) {
		String jpql="select d from Dish d where d.mId=:mId and d.dName like :dName";
		List<Dish> dishList = entitymanager.createQuery(jpql)
				.setParameter("mId", mId)
				.setParameter("dName", "%"+dName+"%")
				.getResultList();
		return dishList;
	}

	@Override
	public List<Dish> findDishesByBoth(Integer dType, String dName, String mId) {
		String jpql="select d from Dish d where d.mId=:mId and d.dName like :dName and d.dType=:dType";
		List<Dish> dishList = entitymanager.createQuery(jpql)
				.setParameter("mId", mId)
				.setParameter("dName", "%"+dName+"%")
				.setParameter("dType", dType)
				.getResultList();
		return dishList;
	}

	@Override
	public Dish findDishById(String dId) {
		Dish dish = entitymanager.find(Dish.class, dId);
		return dish;
	}

	
	
}
