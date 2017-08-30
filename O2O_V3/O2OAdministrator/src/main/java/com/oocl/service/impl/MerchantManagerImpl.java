package com.oocl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.MerchantApplyRecordDao;
import com.oocl.dao.MerchantDao;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.MerchantApplyRecord;
import com.oocl.service.MerchantManager;

@Service("merchantManager")
public class MerchantManagerImpl implements MerchantManager{
	@Resource(name="merchantDao")
	private MerchantDao merchantDao;
	@Resource(name="recordDao")
	private MerchantApplyRecordDao recordDao;

	@Transactional
	@Override
	public Merchant add(Merchant t) {
		return merchantDao.add(t);
	}

	@Transactional
	@Override
	public Merchant update(Merchant t) {
		if(t==null){
			return null;
		}
		if(t.getId()==null){
			return null;
		}
		return merchantDao.update(t);
	}

	@Transactional
	@Override
	public Merchant delete(String id) {
		if(id==null){
			return null;
		}
		return merchantDao.delete(id);
	}

	@Override
	public List<Merchant> findAll() {
		return merchantDao.findAll();
	}

	@Override
	public Merchant findMerchantById(String id) {
		if(id==null){
			return null;
		}
		return merchantDao.findMerchantById(id);
	}

	@Override
	public int getStatusById(String id) {
		if(id==null){
			return -1;
		}
		return merchantDao.getStatusById(id);
	}

	@Override
	public List<Merchant> findMerchantByStatus(Integer status) {
		return merchantDao.findMerchantByStatus(status);
	}

	@Override
	public List<Merchant> findMerchantByIsban(Boolean isBan) {
		return merchantDao.findMerchantByBan(isBan);
	}

	@Transactional
	@Override
	public int updateStatus(String id, Integer status) {
		return merchantDao.updateStatus(id, status);
	}

	@Transactional
	@Override
	public int updateIsBan(String id, Boolean isBan) {
		return merchantDao.updateBan(id, isBan);
	}

	@Override
	public List<MerchantApplyRecord> findHistory(String id) {
		return recordDao.findByMerchantId(id);
	}

	

}
