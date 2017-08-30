package com.oocl.service;

import java.util.Date;
import java.util.List;
import com.oocl.pojo.Complaint;

public interface ComplaintManager {
	public Complaint add(Complaint complaint);
	
	public List<Complaint> findByMid(String mid);
	
	public List<Complaint> findByTime(String mid, Date from, Date to);
	
	public List<Complaint> findAll();
}
