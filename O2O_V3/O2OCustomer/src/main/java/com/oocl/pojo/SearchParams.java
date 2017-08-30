package com.oocl.pojo;

public class SearchParams {
	private String mid;
	private Integer dtype;
	private String keyword;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public Integer getDtype() {
		return dtype;
	}

	public void setDtype(Integer dtype) {
		this.dtype = dtype;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public SearchParams(String mid, Integer dtype, String keyword) {
		super();
		this.mid = mid;
		this.dtype = dtype;
		this.keyword = keyword;
	}

	public SearchParams() {
		super();
	}

}
