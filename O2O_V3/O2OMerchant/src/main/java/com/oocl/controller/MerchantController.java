package com.oocl.controller;



import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.oocl.activemq.Producer;
import com.oocl.memory.SystemMemory;
import com.oocl.pojo.Application;
import com.oocl.pojo.Constants;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.MenuItem;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Protocol;
import com.oocl.pojo.State;
import com.oocl.service.MenuItemService;
import com.oocl.service.MerchantService;
import com.oocl.util.CommonURL;
import com.oocl.util.HTTPConnUtil;
import com.oocl.util.JSONUtil;
import com.oocl.util.StatusCode;

/**
 * Please remove this file when you add other files into this package
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController {
	private Logger logger = Logger.getLogger(MerchantController.class);
	
	@Resource(name="merchantServiceImpl")
	private MerchantService merchantService;
	
	@Resource(name="menuItemServiceImpl")
	private MenuItemService menuItemService;
	
	@Resource(name="applicationProducer")
	private Producer producer;
	
	
	@ResponseBody//原封不动返回响应体
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public JSONResponse<Merchant> regist(MultipartHttpServletRequest multiRequest){
		JSONResponse<Merchant> response = new JSONResponse<Merchant>();
		logger.info("merchant---regist");
		response = merchantService.regist(multiRequest);
//		Application application = new Application(mId, mName, mIdCardBase64, mHeadBase64, mAddr, mStatus, false, null);
		if (response.getResult()==null) {
			return response;
		}else {
			Merchant merchant = response.getResult();
			Application application = new Application(merchant.getId(), merchant.getmName(), merchant.getmAccount(),
					merchant.getImgCard(), merchant.getImgHead(), merchant.getAddress(), 
					merchant.getStatus(), false, null);
			Protocol protocol = new Protocol();
			protocol.setObject(application);
			if (producer.getJMSConnection()==null) {
				response.setCode(StatusCode.A_SERVER_DOWN);
				return response;
			}
			producer.sendMsgToJMS(JSONUtil.toJSON(protocol));
		}
		return response;
	}
	
	@ResponseBody//原封不动返回响应体
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public JSONResponse<Merchant> login(String mAccount,String pwd,HttpSession session){
		JSONResponse<Merchant> response = new JSONResponse<Merchant>();
		response = merchantService.login(mAccount, pwd);
		Merchant merchant = response.getResult();
		if (merchant!=null) {
			/**获取审核状态
			Protocol protocol = HTTPConnUtil.sendGet(CommonURL.GET_STATION+merchant.getId());
			if (protocol==null) {
				//Server A Down;
				response.setCode(StatusCode.A_SERVER_DOWN);
				return response;
			}
			State state =(State)protocol.getObject();
			if (state!=null) {
				if(state.getState()==Constants.APPLY_REJECTED){
					//reject
					session.setAttribute("feedback", state.getContent());
				}
				merchant.setStatus(state.getState());
			}
			*/
			
			session.setAttribute("merchant", merchant);
		}
		return response;
	}
	
	@RequestMapping(value="/sec/homePage")
	public String homePage(Model model,HttpSession session){
		Merchant merchant = (Merchant)session.getAttribute("merchant");
		
		return "homePage";
	}
	
	@RequestMapping(value="/sec/menuList")
	public String menuList(Model model){
		List<MenuItem> list = menuItemService.findAllMenuItem();
		model.addAttribute("menuList", list);
		return "menuList";
	}
	
	@RequestMapping(value="/sec/toolbar")
	public String toolbar(Model model){
//		model.addAttribute("", attributeValue)
		return "toolbar";
	}
	
	@RequestMapping(value="/sec/message")
	public String merchantMesaage(Model model){
		
		return "message";
	}
	
	@RequestMapping(value="/sec/logout")
	public String logout(HttpSession session){
		session.setAttribute("merchant", null);
		return "redirect:/login.jsp";
	}
	
	@ResponseBody//原封不动返回响应体
	@RequestMapping(value="/sec/getState",method=RequestMethod.GET)
	public JSONResponse<State> getState(HttpSession session){
		JSONResponse<State> response = new JSONResponse<State>();
		Merchant merchant =(Merchant) session.getAttribute("merchant");
			
		Protocol protocol = HTTPConnUtil.sendGet(CommonURL.GET_STATION+merchant.getId());
		if (protocol==null) {
			//Server A Down;
			response.setCode(StatusCode.A_SERVER_DOWN);
			return response;
		}
		LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) protocol.getObject();
		State state =new State((Integer)linkedHashMap.get("state"),(String)linkedHashMap.get("content"));
		if (state!=null) {
			if (state.getState()==-1) {
				state.setState(Constants.APPLY_PENDING);
				merchant.setStatus(Constants.APPLY_PENDING);
			}else {
				if(state.getState()==Constants.APPLY_REJECTED){
					//reject
					session.setAttribute("feedback", state.getContent());
				}
				merchant.setStatus(state.getState());
			}
			
		}
			
		session.setAttribute("merchant", merchant);
		response.setResult(state);
		return response;
	} 
	
	@ResponseBody//原封不动返回响应体
	@RequestMapping(value="/sec/getApplyMsg",method=RequestMethod.GET)
	public JSONResponse<Merchant> getApplyMsg(HttpSession session){
		JSONResponse<Merchant> response = new JSONResponse<Merchant>();
		Merchant m = (Merchant)session.getAttribute("merchant");
		response = merchantService.getApplyMsg(m.getId());
		return response;
	}
	
	@ResponseBody//原封不动返回响应体
	@RequestMapping(value="/sec/applyStore",method=RequestMethod.POST)
	public JSONResponse<Merchant> applyStore(MultipartHttpServletRequest multiRequest,HttpSession session){
		JSONResponse<Merchant> response = new JSONResponse<Merchant>();
		Merchant oldMerchant =(Merchant) session.getAttribute("merchant");
		response = merchantService.applyStore(multiRequest, oldMerchant);
		if (response.getResult()==null) {
			return response;
		}else {
			Merchant merchant = response.getResult();
			Application application = new Application(merchant.getId(), merchant.getmName(), merchant.getmAccount(),
					merchant.getImgCard(), merchant.getImgHead(), merchant.getAddress(), 
					merchant.getStatus(), false, null);
			Protocol protocol = new Protocol();
			protocol.setObject(application);
			if (producer.getJMSConnection()==null) {
				response.setCode(StatusCode.A_SERVER_DOWN);
				return response;
			}
			producer.sendMsgToJMS(JSONUtil.toJSON(protocol));
		}
		session.setAttribute("merchant", oldMerchant);
		return response;
	}
	

}
