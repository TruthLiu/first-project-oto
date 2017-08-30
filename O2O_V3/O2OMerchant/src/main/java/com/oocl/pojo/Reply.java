package com.oocl.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.print.DocFlavor.STRING;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reply {
	@Id
	private String id;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="cm_id")
	private Comment comment;
	private String content;
	private Date createTime;
	
	public Reply() {
		super();
	}
	
	public Reply(String id,  String content, Date createTime) {
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
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
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
