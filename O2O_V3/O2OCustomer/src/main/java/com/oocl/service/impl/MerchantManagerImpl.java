package com.oocl.service.impl;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.CustomerDao;
import com.oocl.dao.MerchantDao;
import com.oocl.pojo.Merchant;
import com.oocl.service.MerchantManager;


@Service("merchantManagerImpl")
public class MerchantManagerImpl implements MerchantManager {

	@Resource(name = "merchantDaoImpl")
	private MerchantDao merchantDao;

	@Override
	public Merchant findOneMerchantByName(String mName) {
		return merchantDao.findOneMerchantByMName(mName);
	}

	@Override
	public Merchant findOneMerchantById(String mId) {
		return merchantDao.findOneMerchantByMId(mId);
	}

	@Override
	public List<Merchant> findMerchantsByMIdList(String Name, Integer dType,
			Collection<String> mIdList) {
		List<Merchant> ml = new ArrayList<Merchant>();
		Merchant merchant = null;
		if(dType == 0){
			if(Name==null){	//dType=0,dName=null,搜索所有过审非拉黑商家
				for(String mId:mIdList){
					merchant = merchantDao.findOneMerchantByMId(mId);
					if(merchant!=null)ml.add(merchant);
				}
			}
			else{			//dType=0,dName!=null,搜索所有含有名字带dName关键字菜品的过审非拉黑用户
				for(String mId:mIdList){
					merchant = merchantDao.findOneMerchantByDNameAndMId(Name, mId);
					if(merchant!=null)ml.add(merchant);
				}
			}
		}
		else{					
			if(Name==null){	//dType!=0,dName==null,搜索所有含有dType类型菜品的过审非拉黑商家
				for(String mId:mIdList){
					merchant = merchantDao.findOneMerchantByDTypeAndMId(dType, mId);
					if(merchant!=null)ml.add(merchant);
				}
			}
			else{			//dType!=0,dName==!null,搜索所有含有dType类型且名字带dName关键字的菜品的过审非拉黑商家
				for(String mId:mIdList){
					merchant = merchantDao.findOneMerchantByAllThree(Name, dType, mId);
					if(merchant!=null)ml.add(merchant);
				}
			}
		}
		return ml;
	}

	@Override
	@Transactional
	public List<String> MerchantsIdFilterByStatus(Collection<String> mIdList) {
		List<String> mIdListFiltered = new ArrayList<String>();
		String mIdFiltered = null;
		if (mIdList != null) {
			for(String mid : mIdList){
				mIdFiltered = merchantDao.MerchantIdFilterByStatus(mid);
				if(mIdFiltered!=null)mIdListFiltered.add(mIdFiltered);
			}
		}
		return mIdListFiltered;
	}

	@Override
	@Transactional
	public Merchant UpdateMerchantStar(String mId) {
		return merchantDao.UpdateMerchantStar(mId);
	}

	@Override
	@Transactional
	public Double getMerchantStar(String mId) {
		return merchantDao.getMerchantStar(mId);
	}
	
	
	
	
}
