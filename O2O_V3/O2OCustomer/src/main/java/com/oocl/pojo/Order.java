package com.oocl.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@Column(length=36)
	private String id;
	
	private String addr;
	
	@Column(length=20)
	private String realName;
	
	@Column(length=20)
	private String phone;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	private int status;
	
	private Double totalPrice;
	
	@ManyToOne
	@JoinColumn(name="c_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="m_id")
	private Merchant merchant;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="order",cascade=CascadeType.REMOVE)
	private Comment comment;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "order")
	private Complaint complaint;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="order",cascade=CascadeType.REMOVE)
	private Set<OrderItem> items=new HashSet<OrderItem>();
	
	
	

	public Order() {
		super();
	}

	public Order(String id, String addr, String realName, String phone,
			Date createTime, int status, Double totalPrice, Customer customer,
			Merchant merchant, Comment comment, Complaint complaint,
			Set<OrderItem> items) {
		super();
		this.id = id;
		this.addr = addr;
		this.realName = realName;
		this.phone = phone;
		this.createTime = createTime;
		this.status = status;
		this.totalPrice = totalPrice;
		this.customer = customer;
		this.merchant = merchant;
		this.comment = comment;
		this.complaint = complaint;
		this.items = items;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	

	
}
