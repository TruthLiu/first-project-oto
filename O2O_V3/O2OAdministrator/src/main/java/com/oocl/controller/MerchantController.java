package com.oocl.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.constant.ApplyStatus;
import com.oocl.dto.DTO;
import com.oocl.dto.Process;
import com.oocl.pojo.Merchant;
import com.oocl.service.MerchantManager;
import com.oocl.util.DESUtil;
import com.oocl.util.HttpConUtil;

import static com.oocl.util.IPUtil.getProperty;

@Controller
@RequestMapping("/sec/")
public class MerchantController {
	@Resource(name="merchantManager")
	private MerchantManager manager;
	
	private Logger logger = Logger.getLogger(MerchantController.class);
	
	@RequestMapping("home")
	public String home(HttpServletRequest request,HttpServletResponse response){
		return "home";
	}
	
	@RequestMapping("merchant")
	public String merchantHome(HttpServletRequest request,HttpServletResponse response){
		return "views/merchant/merchantMag";
	}
	
	//查询所有待审核列表
	@RequestMapping("pendingList")
	@ResponseBody
	public DTO<Merchant> findPendingList(HttpServletRequest request){
		logger.info("admin user view the pengding list page");
		
		ServletContext context = request.getSession().getServletContext();
		Object obj = context.getAttribute("pendingCount");
		if(obj!=null){
			context.removeAttribute("pendingCount");
		}
		List<Merchant> merchants = manager.findMerchantByStatus(ApplyStatus.PENGDING);
		DTO<Merchant> dto = new DTO<Merchant>(null, merchants, true);
		return dto;
	}
	
	//查询所有审核通过列表
	@RequestMapping("pass")
	@ResponseBody
	public DTO<Merchant> findPassList(){
		logger.info("admin user view the pass list page");
		List<Merchant> merchants = manager.findMerchantByStatus(ApplyStatus.APPROVED);
		DTO<Merchant> dto = new DTO<Merchant>(null, merchants, true);
		return dto;
	}
	
	//查询所有审核拒绝列表
	@RequestMapping("reject")
	@ResponseBody
	public DTO<Merchant> findRejectList(){
		logger.info("admin user view the reject list page");
		List<Merchant> merchants = manager.findMerchantByStatus(ApplyStatus.REJECTED);
		DTO<Merchant> dto = new DTO<Merchant>(null, merchants, true);
		return dto;
	}
	
	@RequestMapping("detail")
	@ResponseBody
	public DTO<Merchant> findDetailMerchat(String mid){
		logger.info("admin user view the merchant detail page");
		if(mid!=null){
			Merchant merchant = manager.findMerchantById(mid);
			if(merchant!=null){
				DTO<Merchant> dto = new DTO<Merchant>(merchant, null, true);
				return dto;
			}
		}
		DTO<Merchant> dto = new DTO<Merchant>(null, null, false);
		return dto;
	}
	
	@RequestMapping("toProcess")
	@ResponseBody
	public DTO<Merchant> toProcess(String mid){
		logger.info("admin user view the pending merchant to process");
		if(mid!=null){
			Merchant merchant = manager.findMerchantById(mid);
			if(merchant!=null){
				DTO<Merchant> dto = new DTO<Merchant>(merchant, null, true);
				return dto;
			}
		}
		DTO<Merchant> dto = new DTO<Merchant>(null, null, false);
		return dto;
	}
	

	@RequestMapping("listen")
	@ResponseBody
	public DTO<Integer> listener(String mid,HttpServletRequest request){
		ServletContext context = request.getSession().getServletContext();
		Object obj = context.getAttribute("pendingCount");
		if(null==obj){
			DTO<Integer> dto = new DTO<Integer>(null, null, false);
			return dto;
		}
		int n = ((Integer)context.getAttribute("pendingCount")).intValue();
		
		DTO<Integer> dto = new DTO<Integer>(Integer.valueOf(n), null, true);
		return dto;
	}
	
	@RequestMapping("ban")
	@ResponseBody
	public DTO<String> banMerchant(String change, String mid) throws UnsupportedEncodingException{
		String ADDREESS = String.format("http://%s:%s/O2OMerchant/listen/ban", getProperty("ip"), getProperty("port"));
		int m=0;
//		DESUtil desUtil = new DESUtil();
//		String emid = desUtil.encrypt(mid, "UTF-8");
    	if((change!=null) && (change.equals("black"))){
    		m=manager.updateIsBan(mid, true);
    		String address=ADDREESS+"?id="+mid+"&isBan="+true;
    		System.out.println(address);
    		HttpConUtil.sendBanState(address);
    	}
    	if((change!=null) && (change.equals("white"))){
    		m=manager.updateIsBan(mid, false);
    		String address=ADDREESS+"?id="+mid+"&isBan="+false;
    		System.out.println(address);
    		HttpConUtil.sendBanState(address);
    	}
		if(m>0){
			DTO<String> dto = new DTO<String>(mid, null, true);
			logger.info("ban merchant");
			return dto;
		}else{
			DTO<String> dto = new DTO<String>(mid, null, false);
			logger.info("ban merchant failed");
			return dto;
		}
	}
	
	@RequestMapping("process")
	@ResponseBody
	public DTO<String> process (@RequestBody Process process){
		logger.info("submit to process merchant apply");

		String result = process.getResult();
		String id = process.getId();
		if("1".equals(result)){
			manager.updateStatus(id, ApplyStatus.APPROVED);
		}else if("2".equals(result)){
			String bcontent = process.getbContent();
			if(null==bcontent || "".equals(bcontent)){
				DTO<String> dto = new DTO<String>("必须填写驳回理由", null, false);
				return dto;
			}
			if(bcontent.length()>100){
				DTO<String> dto = new DTO<String>("驳回理由必须在一百字以内", null, false);
				return dto;
			}
			Merchant merchant = manager.findMerchantById(id);
			merchant.setbContent(bcontent);
			merchant.setStatus(ApplyStatus.REJECTED);
			manager.update(merchant);
		}
		DTO<String> dto = new DTO<String>("审核成功", null, true);
		return dto;
	}
	
	
}
