package com.oocl.vo;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.oocl.pojo.Customer;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Order;

public class ComplaintVO {

	private String id;
	
	private String content;
	
	private Date createTime;

	
	
	public ComplaintVO() {
		super();
	}

	public ComplaintVO(String id, String content, Date createTime) {
		super();
		this.id = id;
		this.content = content;
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
