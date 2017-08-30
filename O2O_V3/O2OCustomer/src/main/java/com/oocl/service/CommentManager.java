package com.oocl.service;

import java.util.List;

import com.oocl.pojo.Comment;
import com.oocl.vo.CommentVO;

public interface CommentManager {
	
	
	public List<CommentVO> findCommentByMId(String MId);
	
	/**
	 * 第二个参数为订单ID
	 * */
	public Comment addComment(Comment comment,String orderId);
}
