package com.oocl.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.oocl.dao.MerchantDao;
import com.oocl.pojo.Comment;
import com.oocl.pojo.Merchant;


@Repository(value = "merchantDaoImpl")
public class MerchantDaoImpl implements MerchantDao {

	@PersistenceContext(name="punit")
	private EntityManager entitymanager;
	
	@Override
	public String MerchantIdFilterByStatus(String mId) {
		String jpql="SELECT m.id from Merchant m where m.id =:mid and m.status = 1";
		List<String> midList = entitymanager.createQuery(jpql)
				.setParameter("mid", mId)
				.getResultList();
		if(midList.isEmpty())return null;
		
		return mId;
	}

	@Override
	public Merchant findOneMerchantByMId(String mId) {
		Merchant merchant = entitymanager.find(Merchant.class, mId);
		return merchant ;
	}

	@Override
	public Merchant findOneMerchantByDTypeAndMId(Integer dType, String mId) {
//		String jpql1="SELECT c FROM Customer c WHERE c.age > (SELECT AVG(c.age)FROM Customer c)";
		String jpql="SELECT m FROM Merchant m WHERE m.id =:id AND m.id IN (SELECT DISTINCT d.mId FROM Dish d WHERE d.dType =:dType)";
		List<Merchant> merchantList = entitymanager.createQuery(jpql)
				.setParameter("id", mId)
				.setParameter("dType", dType)
				.getResultList();
		if(merchantList.isEmpty())return null;
		else return merchantList.get(0);
	}

	@Override
	public Merchant findOneMerchantByDNameAndMId(String Name, String mId) {
		String jpql="SELECT m FROM Merchant m WHERE m.id =:id AND (m.id IN (SELECT DISTINCT d.mId FROM Dish d WHERE d.dName LIKE :dName) OR m.id in (SELECT DISTINCT m.id from Merchant m WHERE m.mName LIKE :mName))";
		List<Merchant> merchantList = entitymanager.createQuery(jpql)
				.setParameter("id", mId)
				.setParameter("dName", "%"+Name+"%")
				.setParameter("mName", "%"+Name+"%")
				.getResultList();
		if(merchantList.isEmpty())return null;
		else return merchantList.get(0);
	}

	@Override
	public Merchant findOneMerchantByAllThree(String Name, Integer dType,
			String mId) {
		String jpql="SELECT m FROM Merchant m WHERE m.id =:id AND m.id IN (SELECT DISTINCT d.mId FROM Dish d WHERE d.dType =:dType) AND (m.id IN (SELECT DISTINCT d.mId FROM Dish d WHERE d.dName LIKE :dName) OR m.id in (SELECT DISTINCT m.id from Merchant m WHERE m.mName LIKE :mName))";
		
		List<Merchant> merchantList = entitymanager.createQuery(jpql)
				.setParameter("id", mId)
				.setParameter("dType", dType)
				.setParameter("dName", "%"+Name+"%")
				.setParameter("mName", "%"+Name+"%")
				.getResultList();
		if(merchantList.isEmpty())return null;
		else return merchantList.get(0);
	}

	@Override
	public Merchant findOneMerchantByMName(String mName) {
		String jpql="SELECT m from Merchant m where m.mName =:mName";
		List<Merchant> merchantList = entitymanager.createQuery(jpql)
				.setParameter("mName", mName)
				.getResultList();
		if(merchantList.isEmpty())return null;
		else return merchantList.get(0);
	}

	@Override
	public Merchant UpdateMerchantStar(String mId) {
		Merchant merchant = entitymanager.find(Merchant.class, mId);
		Set<Comment> commentSet = merchant.getComment();
		int commentCount = commentSet.size();
		Double totalStar = 0.0;
		for(Comment comment:commentSet){
			totalStar+=comment.getStar();
		}
		Double starNew = totalStar/(double)commentCount;
		String jpql = "Update Merchant m set m.star =:starNew where m.id =:mId";
		entitymanager.createQuery(jpql)
		.setParameter("starNew", starNew)
		.setParameter("mId", mId)
		.executeUpdate();
		entitymanager.flush();
		entitymanager.refresh(merchant);
		return merchant;
	}

	@Override
	public Double getMerchantStar(String mId) {
		Merchant merchant = entitymanager.find(Merchant.class, mId);
		Set<Comment> commentSet = merchant.getComment();
		int commentCount = commentSet.size();
		if(commentCount==0)return 3.0;
		Double totalStar = 0.0;
		for(Comment comment:commentSet){
			totalStar+=comment.getStar();
		}
		Double starNew = totalStar/(double)commentCount;
		return starNew;
	}
}
