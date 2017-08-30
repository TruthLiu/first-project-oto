package com.oocl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.MenuItemDao;
import com.oocl.pojo.MenuItem;
import com.oocl.service.MenuItemService;

@Service(value="menuItemServiceImpl")
public class MenuItemServiceImpl implements MenuItemService{
	
	private Logger logger = Logger.getLogger(MerchantServiceImpl.class);
	@Resource(name="menuItemDaoImpl")
	private MenuItemDao menuItemDao;

	@Override
	@Transactional
	public MenuItem addMenuItem(MenuItem menu) {
		return menuItemDao.addMenuItem(menu);
	}

	@Override
	public List<MenuItem> findAllMenuItem() {
		return menuItemDao.findAllMenuItem();
	}

}
