package com.oocl.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.print.DocFlavor.STRING;

@Entity
public class Reply {
	@Id
	private String id;
	@OneToOne
	@JoinColumn(name="cm_id")
	private Comment comment;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	
	public Reply() {
		super();
	}
	
	public Reply(String id, Comment comment, String content, Date createTime) {
		super();
		this.id = id;
		this.comment = comment;
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
