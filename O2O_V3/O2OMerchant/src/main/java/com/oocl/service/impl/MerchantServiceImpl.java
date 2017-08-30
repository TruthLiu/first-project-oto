package com.oocl.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.oocl.dao.MerchantDao;
import com.oocl.pojo.Constants;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;
import com.oocl.service.MerchantService;
import com.oocl.util.FileUploadUtil;
import com.oocl.util.StatusCode;

@Service(value="merchantServiceImpl")
public class MerchantServiceImpl implements MerchantService {
	private Logger logger = Logger.getLogger(MerchantServiceImpl.class);
	@Resource(name="merchantDaoImpl")
	private MerchantDao merchantDao;

	

	@Override
	public JSONResponse<Merchant> login(String mAccount, String pwd) {
		JSONResponse<Merchant> response = new JSONResponse<Merchant>();
		Merchant merchant = merchantDao.findMerchantByAccount(mAccount);
		if (merchant==null) {
			response.setCode(StatusCode.FAIL);
			response.setMsg("User not exits");
			return response;
		}
		if (!merchant.getPwd().equals(pwd)) {
			response.setCode(StatusCode.FAIL);
			response.setMsg("Password not right");
			return response;
		}
		response.setCode(StatusCode.SUCCESS);
		response.setResult(merchant);
		return response;
	}



	@Override
	@Transactional
	public JSONResponse<Merchant> regist(MultipartHttpServletRequest multiRequest) {
		JSONResponse<Merchant> response = new JSONResponse<Merchant>();
		logger.info("merchant---regist");
		//基本信息判断
		String mAccount = multiRequest.getParameter("mAccount");
		String mName = multiRequest.getParameter("mName");
		String pwd = multiRequest.getParameter("pwd");
		String pwd2 = multiRequest.getParameter("pwd2");
		String address = multiRequest.getParameter("address");
		String imgHead="",imgCard="";
		logger.info("mAccount:"+mAccount+",mName:"+mName);
		if (mAccount==null||mAccount.equals("")||merchantDao.findMerchantByAccount(mAccount)!=null) {
			response.setCode(StatusCode.FAIL);
			response.setMsg("account already exits");
			return response;
		}
		Map<String, MultipartFile> uploadFiles = new HashMap<>(); 
        Iterator<String> filesNames = multiRequest.getFileNames(); //获得所有的文件名  
        String id=UUID.randomUUID().toString();
        while(filesNames.hasNext()){    //迭代，对单个文件进行操作  
            String fileName =filesNames.next();  
            MultipartFile file =  multiRequest.getFile(fileName);
            if (file.isEmpty()) {
            	response.setCode(StatusCode.FAIL);
    			response.setMsg(fileName+"is not null");
    			return response;
			}
            if (file.getSize()>1048576) {
            	response.setCode(StatusCode.FAIL);
    			response.setMsg("picture is not bigger than 1MB");
    			return response;
			}
            if (fileName.equals("imgHead")) {
				imgHead = "images/head/"+id+".jpg";
				uploadFiles.put(imgHead, file);
			}else if (fileName.equals("imgCard")) {
				imgCard = "images/idcard/"+id+".jpg";
				uploadFiles.put(imgCard, file);
			} 
        }
        String result = FileUploadUtil.fileUpload(uploadFiles);
        if (result.equals(StatusCode.FILE_SERVER_FAIL)) {
        	response.setCode(StatusCode.FAIL);
			response.setMsg("file server fail");
			return response;
		}
        //校验通过
        Double star = 5.0;
        Merchant merchant = new Merchant(id, mAccount, pwd, mName, address, imgHead, imgCard, star,Constants.APPLY_PENDING);
        merchantDao.addMerchant(merchant);
        response.setCode(StatusCode.SUCCESS);
        response.setResult(merchant);
		return response;
	}

	
	@Override
	@Transactional
	public JSONResponse<Merchant> applyStore(
			MultipartHttpServletRequest multiRequest,Merchant oldMerchant) {
		JSONResponse<Merchant> response = new JSONResponse<Merchant>();
		String mName = multiRequest.getParameter("mName");
		String address = multiRequest.getParameter("address");
		String imgHead="",imgCard="";
		Map<String, MultipartFile> uploadFiles = new HashMap<>(); 
        Iterator<String> filesNames = multiRequest.getFileNames(); //获得所有的文件名  
        while(filesNames.hasNext()){    //迭代，对单个文件进行操作  
            String fileName =filesNames.next();  
            MultipartFile file =  multiRequest.getFile(fileName);
            if (file.isEmpty()) {
            	if (fileName.equals("imgHead")) {
    				imgHead = oldMerchant.getImgHead();
    			}else if (fileName.equals("imgCard")) {
    				imgCard = oldMerchant.getImgHead();
    			}
            	continue;
			}else{
				if (file.getSize()>1048576) {
	            	response.setCode(StatusCode.FAIL);
	    			response.setMsg("picture is not bigger than 1MB");
	    			return response;
				}
				if (fileName.equals("imgHead")) {
					imgHead = "images/head/"+oldMerchant.getId()+".jpg";
					uploadFiles.put(imgHead, file);
				}else if (fileName.equals("imgCard")) {
					imgCard = "images/idcard/"+oldMerchant.getId()+".jpg";
					uploadFiles.put(imgCard, file);
				} 
			}
        }
        if (uploadFiles.size()!=0) {
        	String result = FileUploadUtil.fileUpload(uploadFiles);
            if (result.equals(StatusCode.FILE_SERVER_FAIL)) {
            	response.setCode(StatusCode.FAIL);
    			response.setMsg("file server fail");
    			return response;
    		}
		}
        oldMerchant.setmName(mName);
        oldMerchant.setAddress(address);
        merchantDao.updateMerchant(oldMerchant);
        response.setCode(StatusCode.SUCCESS);
        response.setResult(oldMerchant);
		return response;
	}


	@Override
	public JSONResponse<Merchant> getApplyMsg(String id) {
		JSONResponse<Merchant> response = new JSONResponse<Merchant>();
		Merchant merchant = merchantDao.findMerchantById(id);
		response.setResult(merchant);
		response.setCode(StatusCode.SUCCESS);
		return response;
	}



	


}
