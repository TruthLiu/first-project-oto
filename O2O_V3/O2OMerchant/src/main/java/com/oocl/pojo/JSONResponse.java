package com.oocl.pojo;

import java.util.List;

public class JSONResponse<T> {

	String  code;
	String msg;
	T result;
	List<T> resultList;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
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

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public JSONResponse(String code, String msg, T result, List<T> resultList) {
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
