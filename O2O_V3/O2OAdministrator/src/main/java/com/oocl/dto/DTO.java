package com.oocl.dto;

import java.io.Serializable;
import java.util.List;

public class DTO<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private T obj;
	
	private List<T> objList;
	
	private boolean success;
	
	public DTO(){
		super();
	}

	public DTO(T obj, List<T> objList, boolean success) {
		super();
		this.obj = obj;
		this.objList = objList;
		this.success = success;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public List<T> getObjList() {
		return objList;
	}

	public void setObjList(List<T> objList) {
		this.objList = objList;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
 
}
