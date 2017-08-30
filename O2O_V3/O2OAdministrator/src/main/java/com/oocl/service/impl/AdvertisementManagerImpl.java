package com.oocl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.AdvertisementDao;
import com.oocl.dto.QueryParam;
import com.oocl.pojo.Advertisement;
import com.oocl.service.AdvertisementManager;

@Service("adManager")
public class AdvertisementManagerImpl implements AdvertisementManager{
	@Resource(name="adDao")
	private AdvertisementDao dao;

	@Override
	@Transactional
	public Advertisement add(Advertisement advertisement) {
		return dao.add(advertisement);
	}

	@Override
	@Transactional
	public Advertisement delete(Advertisement advertisement) {
		return dao.delete(advertisement);
	}

	@Override
	@Transactional
	public Advertisement updateStatus(String id, int status) {
		return dao.updateStatus(id, status);
	}

	@Override
	@Transactional
	public Advertisement updateRecommend(String id, int recommend) {
		return dao.updateRecommend(id, recommend);
	}

	@Override
	public List<Advertisement> findByRecommend(int recommend) {
		return dao.findByRecommend(recommend);
	}

	@Override
	public List<Advertisement> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Advertisement> findByStatus(int status) {
		return dao.findByStatus(status);
	}

	@Override
	public Advertisement findByMid(String mid) {
		return dao.findByMid(mid);
	}

	@Override
	public List<Advertisement> findByCondition(Map<String, Object> condition) {
		return dao.findByCondition(condition);
	}

	@Override
	public List<Advertisement> findByCondition(QueryParam param) {
		return dao.findByCondition(param);
	}
	

}
