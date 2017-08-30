package com.oocl.vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.oocl.pojo.Address;
import com.oocl.pojo.Order;

public class CustomerVO {


	private String id;
	
	private String cname;

	public CustomerVO(String id, String cname) {
		super();
		this.id = id;
		this.cname = cname;
	}

	public CustomerVO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}



	
	
	

	
}
