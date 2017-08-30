package com.oocl.vo;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.oocl.pojo.Comment;

public class ReplyVO {
	private String id;
	private String content;
	private Date createTime;
	
	
	
	public ReplyVO() {
		super();
	}

	public ReplyVO(String id, String content, Date createTime) {
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

	public void setContent(String context) {
		this.content = context;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
	
}
