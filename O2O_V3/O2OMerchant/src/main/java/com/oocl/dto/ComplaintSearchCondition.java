package com.oocl.dto;

import java.util.Date;

public class ComplaintSearchCondition {
	private String mid;
	private String startTime;
	private String endTime;
	private boolean all;
	
	public ComplaintSearchCondition(){
		super();
	}
	
	public ComplaintSearchCondition(String mid, String startTime, String endTime,
			boolean all) {
		super();
		this.mid = mid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.all = all;
	}

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
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
