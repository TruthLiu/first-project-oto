package com.oocl.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.text.AbstractDocument.Content;

@Entity
@Table(name="complaints")
public class Complaint {
	@Id
	private String id;
	
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@ManyToOne
	@JoinColumn(name="c_id")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name="o_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="m_id")
	private Merchant merchant;

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
	
	
}
