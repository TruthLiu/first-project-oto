package com.oocl.dao;

import com.oocl.pojo.Complaint;

public interface ComplaintDao {
	public Complaint addComplaint(Complaint complaint,String orderId);
}
