package com.oocl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.CommentDao;
import com.oocl.dao.ComplaintDao;
import com.oocl.pojo.Complaint;
import com.oocl.service.ComplaintManager;


@Service("complaintManagerImpl")
public class ComplaintManagerImpl implements ComplaintManager{
	
	@Resource(name = "complaintDaoImpl")
	private ComplaintDao complaintDao;

	@Override
	@Transactional
	public Complaint addComplaint(Complaint complaint, String orderId) {
		return complaintDao.addComplaint(complaint, orderId);
	}
	
	
	
}
