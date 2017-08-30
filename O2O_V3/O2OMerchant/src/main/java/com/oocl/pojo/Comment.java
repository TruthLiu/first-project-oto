package com.oocl.pojo;

import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	private String id;
	
	private Double star;
	
	@Column(length=50)
	private String content;
	
	
	
	@OneToOne(mappedBy="comment",fetch=FetchType.EAGER)
	private Reply reply;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="o_id")
	private Order order;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_id")
	private Merchant merchant;
	
	
	@OneToOne(mappedBy="comment",fetch=FetchType.EAGER)
	@ManyToOne
	@JoinColumn(name="c_id")
	private Customer customer;

	@Temporal(TemporalType.DATE)
	private Date createTime;
	
	
	public Comment() {
		super();
	}

	

	public Comment(String id, Double star, String content, Reply reply,
			Order order, Merchant merchant, Customer customer, Date createTime) {
		super();
		this.id = id;
		this.star = star;
		this.content = content;
		this.reply = reply;
		this.order = order;
		this.merchant = merchant;
		this.customer = customer;
		this.createTime = createTime;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getStar() {
		return star;
	}

	public void setStar(Double star) {
		this.star = star;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	
}
