package com.oocl.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="customers")
public class Customer {
	@Id
	@Column
//	(length=36)
	private String id;
	
	@Column(length=20)
	private String cname;
	
	@Column(length=32)
	private String cpwd;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="customer",cascade=CascadeType.PERSIST)
	@JsonIgnore
	private Set<Address> caddrs = new HashSet<Address>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
	@OrderBy("createTime DESC")
	@JsonIgnore
	private Set<Order> orders = new HashSet<Order>();

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

	public String getCpwd() {
		return cpwd;
	}

	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}

	public Set<Address> getCaddrs() {
		return caddrs;
	}

	public void setCaddrs(Set<Address> caddrs) {
		this.caddrs = caddrs;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Customer(String id, String cname, String cpwd, Set<Address> caddrs,
			Set<Order> orders) {
		super();
		this.id = id;
		this.cname = cname;
		this.cpwd = cpwd;
		this.caddrs = caddrs;
		this.orders = orders;
	}

	public Customer() {
		super();
	}
}
