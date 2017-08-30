package com.oocl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oocl.dao.MenuItemDao;
import com.oocl.pojo.MenuItem;
import com.oocl.pojo.Merchant;

@Repository(value="menuItemDaoImpl")
public class MenuItemDaoImpl implements MenuItemDao{
	
	private Logger logger = Logger.getLogger(MerchantDaoImpl.class);
	@PersistenceContext(name="punit")
	private EntityManager em;

	@Override
	public MenuItem addMenuItem(MenuItem menu) {
		em.persist(menu);
		return null;
	}

	@Override
	public List<MenuItem> findAllMenuItem() {
		String jpql="select m from MenuItem m order by m.sort";
		List<MenuItem> ls=em.createQuery(jpql)
				.getResultList();
		return ls;
	}

}
