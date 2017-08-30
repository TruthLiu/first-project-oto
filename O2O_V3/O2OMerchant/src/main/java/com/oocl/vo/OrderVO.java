package com.oocl.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.oocl.pojo.Comment;
import com.oocl.pojo.Complaint;
import com.oocl.pojo.Customer;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.OrderItem;

public class OrderVO {

	private String id;
	
	private String addr;
	
	private String realName;
	
	private String phone;
	
	private Date createTime;
	
	private int status;
	
	private Double totalPrice;
	
	private CustomerVO customerVO;
	
	private MerchantVO merchantVO;
	
	private CommentVO commentVO;
	
	private ComplaintVO complaintVO;
	
	
	
//	private Set<OrderItemVO> itemVO=new HashSet<OrderItemVO>();
	private List<OrderItemVO> itemVO;


	
	

	public OrderVO() {
	super();
}





	public OrderVO(String id, String addr, String realName, String phone,
			Date createTime, int status, Double totalPrice,
			CustomerVO customerVO, MerchantVO merchantVO, CommentVO commentVO,
			ComplaintVO complaintVO, List<OrderItemVO> itemVO) {
		super();
		this.id = id;
		this.addr = addr;
		this.realName = realName;
		this.phone = phone;
		this.createTime = createTime;
		this.status = status;
		this.totalPrice = totalPrice;
		this.customerVO = customerVO;
		this.merchantVO = merchantVO;
		this.commentVO = commentVO;
		this.complaintVO = complaintVO;
		this.itemVO = itemVO;
	}





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getAddr() {
		return addr;
	}





	public void setAddr(String addr) {
		this.addr = addr;
	}





	public String getRealName() {
		return realName;
	}





	public void setRealName(String realName) {
		this.realName = realName;
	}





	public String getPhone() {
		return phone;
	}





	public void setPhone(String phone) {
		this.phone = phone;
	}





	public Date getCreateTime() {
		return createTime;
	}





	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}





	public int getStatus() {
		return status;
	}





	public void setStatus(int status) {
		this.status = status;
	}





	public Double getTotalPrice() {
		return totalPrice;
	}





	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}





	public CustomerVO getCustomerVO() {
		return customerVO;
	}





	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}





	public MerchantVO getMerchantVO() {
		return merchantVO;
	}





	public void setMerchantVO(MerchantVO merchantVO) {
		this.merchantVO = merchantVO;
	}





	public CommentVO getCommentVO() {
		return commentVO;
	}





	public void setCommentVO(CommentVO commentVO) {
		this.commentVO = commentVO;
	}





	public ComplaintVO getComplaintVO() {
		return complaintVO;
	}





	public void setComplaintVO(ComplaintVO complaintVO) {
		this.complaintVO = complaintVO;
	}





	public List<OrderItemVO> getItemVO() {
		return itemVO;
	}





	public void setItemVO(List<OrderItemVO> itemVO) {
		this.itemVO = itemVO;
	}

	
	
	
	
	
}
