package com.oocl.dao;

import java.util.List;

import com.oocl.pojo.MenuItem;

public interface MenuItemDao {
	public MenuItem addMenuItem(MenuItem menu);
	
	public List<MenuItem> findAllMenuItem();
}
