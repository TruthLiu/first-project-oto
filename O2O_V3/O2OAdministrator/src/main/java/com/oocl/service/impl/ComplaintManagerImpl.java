package com.oocl.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.ComplaintDao;
import com.oocl.pojo.Complaint;
import com.oocl.service.ComplaintManager;

@Service("complaintManager")
public class ComplaintManagerImpl implements ComplaintManager{
	@Resource(name="complaintDao")
	private ComplaintDao dao;
	
	@Override
	@Transactional
	public Complaint add(Complaint complaint) {
		return dao.add(complaint);
	}

	@Override
	public List<Complaint> findByMid(String mid) {
		return dao.findByMid(mid);
	}

	@Override
	public List<Complaint> findByTime(String mid, Date from, Date to) {
		return dao.findByTime(mid, from, to);
	}

	@Override
	public List<Complaint> findAll() {
		return dao.findAll();
	}

}
