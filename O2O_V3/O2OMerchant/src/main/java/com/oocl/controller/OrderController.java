package com.oocl.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.pojo.Comment;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Order;
import com.oocl.service.OrderService;
import com.oocl.util.StatusCode;
import com.oocl.vo.OrderVO;


@RequestMapping("/order")
@Controller
public class OrderController {
	private Logger logger = Logger.getLogger(MerchantController.class);
	
	@Resource(name="orderServiceImpl")
	private OrderService orderService;
	
	@RequestMapping("sec/orderPage")
	public String orderPage(Model model,HttpSession session){
		return "order";
	}
	
	@ResponseBody
	@RequestMapping("sec/getOrders")
	public JSONResponse<OrderVO> getAllOrder(HttpSession session,int status){
		JSONResponse<OrderVO> response = new JSONResponse<OrderVO>();
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		List<OrderVO> allCommentsList = orderService.findOrders(merchant.getId(), status);
		response.setCode(StatusCode.SUCCESS);
		response.setResultList(allCommentsList);
		return response;
	}
}
