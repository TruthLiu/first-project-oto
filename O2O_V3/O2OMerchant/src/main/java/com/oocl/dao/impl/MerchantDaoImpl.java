package com.oocl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oocl.dao.MerchantDao;
import com.oocl.pojo.Merchant;

@Repository(value="merchantDaoImpl")
public class MerchantDaoImpl implements MerchantDao{
	
	private Logger logger = Logger.getLogger(MerchantDaoImpl.class);
	@PersistenceContext(name="punit")
	private EntityManager em;

	@Override
	public Merchant addMerchant(Merchant m) {
		em.persist(m);
		return m;
	}

	@Override
	public Merchant updateMerchant(Merchant m) {
		Merchant merchant = em.find(Merchant.class, m.getId());
		merchant = m;
		em.merge(merchant);
		return m;
	}

	@Override
	public Merchant findMerchantById(String id) {
		Merchant merchant = em.find(Merchant.class, id);
		if (merchant==null) {
			return null;
		}
		return merchant;
	}

	@Override
	public Merchant findMerchantByName(String mName) {
		String jpql="select m from Merchant m where m.mname=:mname";
		List<Merchant> ls=em.createQuery(jpql)
				.setParameter("mname", mName)
				.getResultList();
		if (ls.size()==0) {
			return null;
		}
		return ls.get(0);
	}

	@Override
	public Merchant findMerchantByAccount(String mAccount) {
		String jpql="select m from Merchant m where m.mAccount=:mAccount";
		List<Merchant> ls=em.createQuery(jpql)
				.setParameter("mAccount", mAccount)
				.getResultList();
		if (ls.size()==0) {
			return null;
		}
		return ls.get(0);
	}

}
