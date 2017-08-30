package com.oocl.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.junit.Ignore;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "merchant")
public class Merchant {

	@Id
	@Column(length = 36)
	private String id;
	private String mAccount;
	private String pwd;
	private String mName;
	private String address;
	private String imgHead;
	private String imgCard;
	private Double star;
	private Integer status;
	
	@OneToMany(mappedBy="merchant")
	@JsonIgnore
	@OrderBy("createTime DESC")
	private Set<Order> order;

	@OneToMany(mappedBy="merchant")
	@JsonIgnore
	@OrderBy("createTime DESC")
	private Set<Comment> comment;

	public Merchant() {
		super();
	}

	public Merchant(String id, String mAccount, String pwd, String mName,
			String address, String imgHead, String imgCard, Double star,
			Integer status) {
		super();
		this.id = id;
		this.mAccount = mAccount;
		this.pwd = pwd;
		this.mName = mName;
		this.address = address;
		this.imgHead = imgHead;
		this.imgCard = imgCard;
		this.star = star;
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getmAccount() {
		return mAccount;
	}

	public void setmAccount(String mAccount) {
		this.mAccount = mAccount;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImgHead() {
		return imgHead;
	}

	public void setImgHead(String imgHead) {
		this.imgHead = imgHead;
	}

	public String getImgCard() {
		return imgCard;
	}

	public void setImgCard(String imgCard) {
		this.imgCard = imgCard;
	}

	public Double getStar() {
		return star;
	}

	public void setStar(Double star) {
		this.star = star;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	
}
