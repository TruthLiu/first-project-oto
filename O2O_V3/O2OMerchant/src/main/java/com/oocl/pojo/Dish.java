package com.oocl.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dish")
public class Dish {
	@Id
	@Column(length=36)
	private String id;
	private String dName;
	private Double dPrice;
	private String dImage;
	private Integer dType;
	private String mId;
	
	
	public Dish() {
		super();
	}
	public Dish(String id, String dName, double dPrice, String dImage,
			Integer dType, String mId) {
		super();
		this.id = id;
		this.dName = dName;
		this.dPrice = dPrice;
		this.dImage = dImage;
		this.dType = dType;
		this.mId = mId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public Double getdPrice() {
		return dPrice;
	}
	public void setdPrice(Double dPrice) {
		this.dPrice = dPrice;
	}
	public String getdImage() {
		return dImage;
	}
	public void setdImage(String dImage) {
		this.dImage = dImage;
	}
	public Integer getdType() {
		return dType;
	}
	public void setdType(Integer dType) {
		this.dType = dType;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	@Override
	public String toString() {
		return "Dish [id=" + id + ", dName=" + dName + ", dPrice=" + dPrice
				+ ", dImage=" + dImage + ", dType=" + dType + ", mId=" + mId
				+ "]";
	}
	
	
}
