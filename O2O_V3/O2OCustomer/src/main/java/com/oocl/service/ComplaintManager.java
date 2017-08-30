package com.oocl.service;

import com.oocl.pojo.Complaint;

public interface ComplaintManager {
	
	/**
	 * 第二个参数为订单ID
	 * */
	public Complaint addComplaint(Complaint complaint,String orderId);
}
