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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	/**
	 * order-state:
	 * reject：-1
	 * waiting：0
	 * receive：1
	 * send:2
	 * finish:3
	 * */
	private int status;
	
	@ManyToOne
	@JoinColumn(name="c_id")
	private Customer customer;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="m_id")
	private Merchant merchant;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY, mappedBy="order",cascade=CascadeType.REMOVE)
	private Comment comment;
	
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "order")
	private Complaint complaint;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="order",cascade=CascadeType.REMOVE)
	private Set<OrderItem> items=new HashSet<OrderItem>();
	
	private Double totalPrice;

	public Order(String id, Customer customer, String addr, Merchant merchant,
			String realName, String phone, Date createTime, int status,
			Comment comment, Complaint complaint, Set<OrderItem> items) {
		super();
		this.id = id;
		this.customer = customer;
		this.addr = addr;
		this.merchant = merchant;
		this.realName = realName;
		this.phone = phone;
		this.createTime = createTime;
		this.status = status;
		this.comment = comment;
		this.complaint = complaint;
		this.items = items;
	}

	public Order() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	
}
