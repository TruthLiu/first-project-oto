package com.oocl.pojo;

import java.util.Collection;

public class JSONResponse<T> {

	Integer code;
	String msg;
	T result;
	Collection<T> resultList;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Collection<T> getResultList() {
		return resultList;
	}

	public void setResultList(Collection<T> resultList) {
		this.resultList = resultList;
	}

	public JSONResponse(Integer code, String msg, T result, Collection<T> resultList) {
		super();
		this.code = code;
		this.msg = msg;
		this.result = result;
		this.resultList = resultList;
	}

	public JSONResponse() {
		super();
	}

}
