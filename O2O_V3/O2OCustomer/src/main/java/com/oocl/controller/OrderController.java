package com.oocl.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.activemq.Producer;
import com.oocl.dto.ComplaintDTO;
import com.oocl.dto.Protocol;
import com.oocl.pojo.Comment;
import com.oocl.pojo.Complaint;
import com.oocl.pojo.Customer;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Order;
import com.oocl.service.CommentManager;
import com.oocl.service.ComplaintManager;
import com.oocl.service.OrderManager;
import com.oocl.vo.OrderVO;

@Controller
public class OrderController {
	
	@Resource(name="complaintProducer")
	private Producer producer;
	
	@Resource(name="orderManagerImpl")
	private OrderManager orderManager;
	
	@Resource(name="commentManagerImpl")
	private CommentManager commentManager;
	
	@Resource(name="complaintManagerImpl")
	private ComplaintManager complaintManager;
	
	@RequestMapping(value="/order", method=RequestMethod.GET)
	public String showOrderListPage(Model model, HttpSession sess) {
		Customer c = (Customer) sess.getAttribute("loginCustomer");
		if (c != null) {
			List<OrderVO> orders = orderManager.findGeneralOrderList(c.getId());
			model.addAttribute("orders", orders);
		}
		return "orderlist";
	}

	@RequestMapping(value="/order/{oid}", method=RequestMethod.GET)
	public String showOrderPage(@PathVariable String oid, Model model, HttpSession sess) {
		OrderVO order = orderManager.findDetailedOrder(oid);
		Customer c = (Customer) sess.getAttribute("loginCustomer");
		if (c != null && c.getId().equals(order.getCustomerVO().getId())) {
			model.addAttribute("order", order);
			return "order";
		}
		return "redirect:/order";
	}
	
	@RequestMapping(value="/api/confirm", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> confirmReceived(String oid) {
		Order o = orderManager.comfirmOrderReceived(oid);
		if (o == null) {
			return new JSONResponse<String>(0, "Failed!", null, null);
		}
		return new JSONResponse<String>(1, "Success!", null, null);
	}
	
	@RequestMapping(value="/api/comment", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> sendComment(String oid, String mid, Integer stars, String content, HttpSession sess) {
		Comment comment = new Comment();
		
		comment.setId(UUID.randomUUID().toString());
		
		Order _order = new Order();
		_order.setId(oid);
		comment.setOrder(_order);
		
		Merchant _merchant = new Merchant();
		_merchant.setId(mid);
		comment.setMerchant(_merchant);
		
		Customer c = (Customer) sess.getAttribute("loginCustomer");
		comment.setCustomer(c);
		
		comment.setStar((double) stars);
		comment.setContent(content);
		comment.setCreateTime(Calendar.getInstance().getTime());
		
		commentManager.addComment(comment, oid);
		return new JSONResponse<String>(1, "Success!", null, null);
	}
	
	@RequestMapping(value="/api/complaint", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse<?> sendComplaint(String oid, String mid, String reason, String content, HttpSession sess) {
		Complaint complaint = new Complaint();
		ComplaintDTO complaintDTO = new ComplaintDTO();
		
		complaint.setId(UUID.randomUUID().toString());
		complaintDTO.setId(complaint.getId());
		
		Order _order = new Order();
		_order.setId(oid);
		complaint.setOrder(_order);
		
		Merchant _merchant = new Merchant();
		_merchant.setId(mid);
		complaint.setMerchant(_merchant);
		complaintDTO.setMid(mid);
		
		Customer c = (Customer) sess.getAttribute("loginCustomer");
		complaint.setCustomer(c);
		complaintDTO.setCid(c.getId());
		
		String ctn = (reason == null ? "" : reason) + ": " + content;
		complaint.setContent(ctn);
		complaintDTO.setMsg(ctn);
		
		complaint.setCreateTime(new Date());
		complaintDTO.setTime(complaint.getCreateTime());
		
		// 发送投诉到A端
		if(producer.getJMSConnection()==null){
			return new JSONResponse<String>(0, "Fail to connect to the A!", null, null);
		}
		try {
			producer.sendMsgToJMS(new ObjectMapper().writeValueAsString(new Protocol<ComplaintDTO>(true, complaintDTO)));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 入库
		complaintManager.addComplaint(complaint, oid);
	
		
		
		
		return new JSONResponse<String>(1, "Success!", null, null);
	}
}
