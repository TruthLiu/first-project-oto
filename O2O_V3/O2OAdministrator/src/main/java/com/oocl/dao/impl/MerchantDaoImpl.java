package com.oocl.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.oocl.base.BaseDaoUtil;
import com.oocl.dao.MerchantDao;
import com.oocl.pojo.Merchant;

@Repository("merchantDao")
public class MerchantDaoImpl extends BaseDaoUtil implements MerchantDao{
	@Override
	@Transactional
	public Merchant add(Merchant t) {
		em.persist(t);
		return t;
	}

	@Override
	@Transactional
	public Merchant update(Merchant t) {
		Merchant m = em.find(Merchant.class, t.getId());
		if(m!=null){
			em.merge(t);
			return t;
		}
		return null;
	}

	@Override
	public Merchant delete(String id) {
		Merchant merchant = em.find(Merchant.class, id);
		if(null!=merchant){
			em.remove(merchant);
			return merchant;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Merchant> findAll() {
		List<Merchant> merchants = em.createQuery("from Merchant").getResultList();
		return merchants;
	}

	@Override
	public Merchant findMerchantById(String id) {
		Merchant merchant = em.find(Merchant.class, id);
		return merchant;
	}

	@Override
	public int getStatusById(String id) {
		Integer status = (Integer) em.createQuery("select m.status from Merchant m where m.id=:id")
					.setParameter("id", id)
					.getSingleResult();
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Merchant> findMerchantByStatus(Integer status) {
		List<Merchant> merchants = em.createQuery("select m from Merchant m where m.status=:status")
					.setParameter("status", status)
					.getResultList();
		return merchants;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Merchant> findMerchantByBan(Boolean isBan) {
		List<Merchant> merchants = em.createQuery("select m from Merchant m where m.ban=:ban")
				.setParameter("ban", isBan)
				.getResultList();
		return merchants;
	}

	@Override
	public int updateStatus(String id, Integer status) {
		Merchant merchant = em.find(Merchant.class, id);
		if(null!=merchant){
			merchant.setStatus(status);
			em.merge(merchant);
			return 1;
		}
		return 0;
	}

	@Override
	public int updateBan(String id, Boolean Ban) {
		Merchant merchant = em.find(Merchant.class, id);
		if(null!=merchant){
			merchant.setBan(Ban);
			em.merge(merchant);
			return 1;
		}
		return 0;
	}

}
