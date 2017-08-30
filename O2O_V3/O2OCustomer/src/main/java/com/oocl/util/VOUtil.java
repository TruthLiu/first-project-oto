package com.oocl.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.oocl.pojo.Comment;
import com.oocl.pojo.Complaint;
import com.oocl.pojo.Customer;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Order;
import com.oocl.pojo.OrderItem;
import com.oocl.pojo.Reply;
import com.oocl.vo.CommentVO;
import com.oocl.vo.ComplaintVO;
import com.oocl.vo.CustomerVO;
import com.oocl.vo.MerchantVO;
import com.oocl.vo.OrderItemVO;
import com.oocl.vo.OrderVO;
import com.oocl.vo.ReplyVO;

public class VOUtil {
	
	public static OrderVO OrderPOToVO(Order order){
		OrderVO orderVO = new OrderVO(order.getId(), order.getAddr(), order.getRealName(), order.getPhone(), 
				order.getCreateTime(), order.getStatus(),order.getTotalPrice(), customerPOToVO(order.getCustomer()), 
				merchantPOToVO(order.getMerchant()), CommentPOToVO(order.getComment()), ComplaintPOToVO(order.getComplaint()), 
				getOrderItemVOList(order.getItems()));
		return orderVO;
	}				
	
	public static OrderVO OrderPOToVO2(Order order){
		OrderVO orderVO = new OrderVO(order.getId(), order.getAddr(), order.getRealName(), order.getPhone(), 
				order.getCreateTime(), order.getStatus(), order.getTotalPrice(),null, 
				merchantPOToVO(order.getMerchant()), null, null, 
				null);
		return orderVO;
	}		
	
	
	public static OrderItemVO OrderItemPOToVO(OrderItem oi){
		OrderItemVO oivo = new OrderItemVO(oi.getId(), oi.getDishName(), oi.getNum(), oi.getUnitPrice());
		return oivo;
	}
	
	public static CommentVO CommentPOToVO(Comment comment){
		if(comment==null) return null;
		CommentVO commentVO = new CommentVO(comment.getId(), comment.getStar(), comment.getContent(), ReplyPOToVO(comment.getReply()),comment.getCreateTime());
		return commentVO;
	}
	
	public static ReplyVO ReplyPOToVO(Reply reply){
		if(reply==null) return null;
		ReplyVO replyVO = new ReplyVO(reply.getId(), reply.getContent(), reply.getCreateTime());
		return replyVO;
	}
	
	public static ComplaintVO ComplaintPOToVO(Complaint complaint){
		if(complaint==null) return null;
		ComplaintVO complaintVO = new ComplaintVO(complaint.getId(), complaint.getContent(), complaint.getCreateTime());
		return complaintVO;
	}
	
	public static MerchantVO merchantPOToVO (Merchant merchant){
		MerchantVO merchantVO = new MerchantVO(merchant.getId(), merchant.getmName());
		return merchantVO;
	}
	
	public static CustomerVO customerPOToVO(Customer customer){
		CustomerVO customerVO = new CustomerVO(customer.getId(), customer.getCname());
		return customerVO;
	}
	
	public static List<OrderItemVO> getOrderItemVOList(Set<OrderItem> orderItems){
		List<OrderItemVO> orderItemVOs = new ArrayList<OrderItemVO>();
		for(OrderItem oi:orderItems){
			orderItemVOs.add(OrderItemPOToVO(oi));
		}
		return orderItemVOs;
	}
	
	
	
}
