package com.oocl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oocl.dao.DishTypeDao;
import com.oocl.pojo.DishType;

@Repository(value="dishTypeDaoImpl")
public class DishTypeDaoImpl implements DishTypeDao{
	private Logger logger = Logger.getLogger(DishDaoImpl.class);
	@PersistenceContext(name="punit")
	private EntityManager em;
	@SuppressWarnings("unchecked")
	@Override
	public List<DishType> getAllDishTypes() {
		String jpql="from DishType";
		return em.createQuery(jpql).getResultList();
	}

}
