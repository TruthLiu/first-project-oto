package com.oocl.service;


import java.util.List;

import com.oocl.pojo.MenuItem;

public interface MenuItemService {

	public MenuItem addMenuItem(MenuItem menu);
	
	public List<MenuItem> findAllMenuItem();
}
