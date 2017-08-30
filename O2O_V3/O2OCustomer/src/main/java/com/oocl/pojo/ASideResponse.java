package com.oocl.pojo;

import java.util.List;
import java.util.Set;

public class ASideResponse<T> {
	private boolean flag;
	private T object;
	private List<T> list;
	private Set<T> set;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public T getObject() {
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

	public Set<T> getSet() {
		return set;
	}

	public void setSet(Set<T> set) {
		this.set = set;
	}

	public ASideResponse(boolean flag, T object, List<T> list, Set<T> set) {
		super();
		this.flag = flag;
		this.object = object;
		this.list = list;
		this.set = set;
	}

	public ASideResponse() {
		super();
	}

}
