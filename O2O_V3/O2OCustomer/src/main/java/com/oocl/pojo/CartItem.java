package com.oocl.pojo;

public class CartItem {
	private String dId;
	private String dName;
	private Integer count;
	private Double price;
	private String dimgpath;

	public String getdId() {
		return dId;
	}

	public void setdId(String dId) {
		this.dId = dId;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDimgpath() {
		return dimgpath;
	}

	public void setDimgpath(String dimgpath) {
		this.dimgpath = dimgpath;
	}

	public CartItem(String dId, String dName, Integer count, Double price,
			String dimgpath) {
		super();
		this.dId = dId;
		this.dName = dName;
		this.count = count;
		this.price = price;
		this.dimgpath = dimgpath;
	}

	public CartItem() {
		super();
	}
	
	/**
	 * count自增1
	 * */
	public void addCount(){
		this.count+=1;
	}
	/**
	 * 若count大于0,自减1,返回true</br>
	 * 若count小于等于0,不作操作,返回false</br>
	 * */
	public boolean reduceCount(){
		if(this.count>0){
			this.count-=1;
			return true;
		}
		else{
			return false;
		}
	}
	
	public Double totalPrice(){
		return ((double)this.count*this.price);
	}

}
