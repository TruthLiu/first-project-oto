package com.oocl.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.base.BaseDaoUtil;
import com.oocl.dao.ComplaintDao;
import com.oocl.pojo.Complaint;

@Repository(value="complaintDao")
public class ComplaintDaoImpl extends BaseDaoUtil implements ComplaintDao{

	@Override
	@Transactional
	public Complaint add(Complaint complaint) {
		em.persist(complaint);
		return complaint;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complaint> findByMid(String mid) {
		String jpql = "select c from Complaint c where c.mid=:mid";
		List<Complaint> complaints = em.createQuery(jpql).setParameter("mid", mid).getResultList();
		return complaints;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complaint> findByTime(String mid, Date from, Date to) {
		String jpql = "select c from Complaint c where c.time>:from and c.time<:to";
		List<Complaint> complaints = em.createQuery(jpql)
										.setParameter("from", from)
										.setParameter("to", to)
										.getResultList();
		return complaints;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complaint> findAll() {
		List<Complaint> coms = em.createQuery("from Complaint").getResultList();
		return coms;
	}

}
