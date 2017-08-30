package com.oocl.dto;

import java.util.List;
import java.util.Set;



public class Protocol<T> {
	private boolean flag;

	private Object object;
	
	private List<T> list;
	
	private Set<Object> set;
	
	public Protocol() {
		// TODO Auto-generated constructor stub
	}

	public Protocol(boolean flag, List<T> list) {
		super();
		this.flag = flag;
		this.list = list;
	}

	public Protocol(boolean flag, Object object) {
		super();
		this.flag = flag;
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Set<Object> getSet() {
		return set;
	}

	public void setSet(Set<Object> set) {
		this.set = set;
	}
	
}
