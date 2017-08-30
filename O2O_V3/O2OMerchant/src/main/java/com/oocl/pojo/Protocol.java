package com.oocl.pojo;

import java.util.List;
import java.util.Set;



public class Protocol <T>{
	
	private boolean flag = true;

	private Object object;
	
	private List<T> list;

	private Set<T> set;
	
	
	public Set<T> getSet() {
		return set;
	}

	public void setSet(Set<T> set) {
		this.set = set;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
