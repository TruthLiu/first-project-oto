package com.oocl.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.oocl.base.BaseDaoUtil;
import com.oocl.dao.AdvertisementDao;
import com.oocl.dto.QueryParam;
import com.oocl.pojo.Advertisement;
import com.oocl.util.QueryCriteriaUtil;

@Repository(value="adDao")
public class AdvertisementDaoImpl extends BaseDaoUtil implements AdvertisementDao{

	@Override
	@Transactional
	public Advertisement add(Advertisement advertisement) {
		em.persist(advertisement);
		return advertisement;
	}

	@Override
	public Advertisement delete(Advertisement advertisement) {
		Advertisement ad = em.find(Advertisement.class, advertisement.getId());
		em.remove(ad);
		return ad;
	}

	@Override
	public Advertisement updateStatus(String id, int status) {
		Advertisement ad = em.find(Advertisement.class, id);
		if(ad!=null){
			ad.setStatus(status);
			em.merge(ad);
			return ad;
		}
		return null;
	}

	@Override
	public Advertisement updateRecommend(String id, int recommend) {
		Advertisement ad = em.find(Advertisement.class, id);
		if(ad!=null){
			ad.setRecommend(recommend);
			em.merge(ad);
			return ad;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Advertisement> findByRecommend(int recommend) {
		String jpql = "select a from Advertisement a where a.recommend=:recommend";
		List<Advertisement> advertisements = em.createQuery(jpql)
												.setParameter("recommend", recommend)
												.getResultList();
		return advertisements;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Advertisement> findAll() {
		String jpql = "from Advertisement a";
		List<Advertisement> advertisements = em.createQuery(jpql).getResultList();
		return advertisements;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Advertisement> findByStatus(int status) {
		String jpql = "select a from Advertisement a where a.status=:status";
		List<Advertisement> advertisements = em.createQuery(jpql)
												.setParameter("status", status)
												.getResultList();
		return advertisements;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Advertisement findByMid(String mid) {
		String jpql = "select a from Advertisement a where a.mid=:mid";
		List<Advertisement> advertisements = em.createQuery(jpql)
												.setParameter("mid", mid)
												.getResultList();
		return advertisements.get(0);
	}

	@Override
	public List<Advertisement> findByCondition(Map<String, Object> condition) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Advertisement> criteria = cb.createQuery(Advertisement.class);
		Root<Advertisement> from = criteria.from(Advertisement.class);
		if(null!=condition){
			List<Predicate> predicates = new ArrayList<>();
			for(String key:condition.keySet()){
				Object object = condition.get(key);
				Predicate predicate = cb.equal(from.get(key), object);
				predicates.add(predicate);
			}
			criteria.where(predicates.toArray(new Predicate[0]));
		}
		TypedQuery<Advertisement> query = em.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Advertisement> findByCondition(QueryParam queryParam) {
		TypedQuery<Advertisement> query = QueryCriteriaUtil.search(Advertisement.class, em, queryParam);
		return query.getResultList();
	}

}
