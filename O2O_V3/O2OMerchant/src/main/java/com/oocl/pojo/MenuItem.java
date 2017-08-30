package com.oocl.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="oscarq",sequenceName="oscar_seq01",initialValue=1,allocationSize=1)
public class MenuItem {

	
	private String itemName;
	private String url;
	private String img;
	@Id
	@GeneratedValue(generator="oscarq",strategy=GenerationType.SEQUENCE)
	private Integer sort;
	
	
	
	
	public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuItem(String itemName, String url, String img, Integer sort) {
		super();
		this.itemName = itemName;
		this.url = url;
		this.img = img;
		this.sort = sort;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	
}
