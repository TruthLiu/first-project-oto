package com.oocl.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.oocl.base.BaseDaoUtil;
import com.oocl.dao.MerchantApplyRecordDao;
import com.oocl.pojo.MerchantApplyRecord;

@Repository("recordDao")
public class MerchantApplyRecordDaoImpl extends BaseDaoUtil implements MerchantApplyRecordDao{

	@Override
	public MerchantApplyRecord add(MerchantApplyRecord t) {
		em.persist(t);
		return t;
	}

	@Override
	public MerchantApplyRecord update(MerchantApplyRecord t) {
		MerchantApplyRecord record = em.find(MerchantApplyRecord.class, t.getId());
		if(record!=null){
			em.merge(t);
			return t;
		}
		return null;
	}

	@Override
	public MerchantApplyRecord delete(String id) {
		MerchantApplyRecord record = em.find(MerchantApplyRecord.class, id);
		if(record!=null){
			em.remove(record);
			return record;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MerchantApplyRecord> findAll() {
		List<MerchantApplyRecord> resultList = em.createQuery("from MerchantApplyRecord")
				.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MerchantApplyRecord> findByMerchantId(String mid) {
		List<MerchantApplyRecord> resultList = em.createQuery("select m from MerchantApplyRecord m where m.mid=:mid")
				.setParameter("mid", mid)
				.getResultList();
		return resultList;
	}

}
