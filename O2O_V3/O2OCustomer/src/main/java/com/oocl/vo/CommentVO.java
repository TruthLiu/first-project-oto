package com.oocl.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.oocl.pojo.Customer;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Order;
import com.oocl.pojo.Reply;

public class CommentVO {
	
	private String id;
	
	private Double star;
	
	private String content;
	
	private ReplyVO replyVO;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	public String getId() {
		return id;
	}

	
	
	public CommentVO() {
		super();
	}



	


	public CommentVO(String id, Double star, String content, ReplyVO replyVO,
			Date createTime) {
		super();
		this.id = id;
		this.star = star;
		this.content = content;
		this.replyVO = replyVO;
		this.createTime = createTime;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public ReplyVO getReplyVO() {
		return replyVO;
	}

	public void setReplyVO(ReplyVO replyVO) {
		this.replyVO = replyVO;
	}
	
	
	
}
