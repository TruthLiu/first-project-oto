package com.oocl.dto;

import java.util.Date;

public class Complaint {
	
	private String id;
	
	private String cid;
	
	private String mid;
	
	private String msg;
	
	private Date time;
	
	public Complaint(){
		super();
	}

	public Complaint(String id, String cid, String mid, String msg, Date time) {
		super();
		this.id = id;
		this.cid = cid;
		this.mid = mid;
		this.msg = msg;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complaint other = (Complaint) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", cid=" + cid + ", mid=" + mid
				+ ", msg=" + msg + ", time=" + time + "]";
	}
}
