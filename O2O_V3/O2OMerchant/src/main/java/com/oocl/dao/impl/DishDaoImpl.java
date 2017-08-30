package com.oocl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oocl.dao.DishDao;
import com.oocl.pojo.Dish;

@Repository(value="dishDaoImpl")
public class DishDaoImpl implements DishDao{
	private Logger logger = Logger.getLogger(DishDaoImpl.class);
	@PersistenceContext(name="punit")
	private EntityManager em;
	@Override
	public Dish addDish(Dish d) {
		em.persist(d);
		return d;
	}

	@Override
	public Dish updateDish(Dish d) {
			em.merge(d);
			return d;
	}

	@Override
	public Dish deleteDish(String id) {
		Dish d=em.find(Dish.class, id);
		if(null!=d){
			em.remove(d);
			return d;
		}
		return null;
	}

	@Override
	public List<Dish> findAllDishes(String mId) {
		String jpql="select d from Dish d where d.mId=:mId ";
		List<Dish> ls=em.createQuery(jpql)
				.setParameter("mId", mId)
				.getResultList();
		return ls;
	}

	@Override
	public List<Dish> findDishesByType(Integer dType, String mId) {
		String jpql="select d from Dish d where d.dType=:dType and d.mId=:mId ";
		List<Dish> ls=em.createQuery(jpql)
				.setParameter("dType", dType)
				.setParameter("mId", mId)
				.getResultList();
		return ls;
	}

	@Override
	public List<Dish> findDishesByName(String dName, String mId) {
		String jpql="select d from Dish d where d.dName=:dName and d.mId=:mId ";
		List<Dish> ls=em.createQuery(jpql)
				.setParameter("dName", dName)
				.setParameter("mId", mId)
				.getResultList();
		return ls;
	}

	@Override
	public List<Dish> findDishesByBoth(Integer dType, String dName, String mId) {
		String jpql="select d from Dish d where d.dType=:dType and d.dName=:dName and d.mId=:mId ";
		List<Dish> ls=em.createQuery(jpql)
				.setParameter("dType", dType)
				.setParameter("dName", dName)
				.setParameter("mId", mId)
				.getResultList();
			return ls;
	}

	@Override
	public Dish findDishById(String dId) {
		Dish d=em.find(Dish.class, dId);
		return d;
	}

}
