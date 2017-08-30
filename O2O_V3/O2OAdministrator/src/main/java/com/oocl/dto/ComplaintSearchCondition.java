package com.oocl.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ComplaintSearchCondition {
	private String mid;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private boolean all;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public boolean isAll() {
		return all;
	}
	public void setAll(boolean all) {
		this.all = all;
	}
	@Override
	public String toString() {
		return "ComplaintSearchCondition [mid=" + mid + ", startTime="
				+ startTime + ", endTime=" + endTime + ", all=" + all + "]";
	}
	
	
	
	
}
