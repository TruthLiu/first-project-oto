package com.oocl.vo;

public class MerchantVO {
	private String id;
	private String mName;

	public MerchantVO() {
		super();
	}

	public MerchantVO(String id, String mName) {
		super();
		this.id = id;
		this.mName = mName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}
	
}
