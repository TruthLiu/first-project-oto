package com.oocl.dao;

import java.util.List;

import com.oocl.base.BaseDao;
import com.oocl.pojo.MerchantApplyRecord;

public interface MerchantApplyRecordDao extends BaseDao<MerchantApplyRecord>{
	
	/**
	 * 根据merchantId查询审核记录
	 * @return
	 */
	public List<MerchantApplyRecord> findByMerchantId(String mid);

}
