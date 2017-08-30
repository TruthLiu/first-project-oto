package com.oocl.dao;

import java.util.List;

import com.oocl.pojo.Comment;
import com.oocl.pojo.Reply;

public interface CommentDao {
	
	public Comment addComment(Comment comment);
	

	/**
	 * id:merchant
	 * */
	public List<Comment> findAllComments(String id);
	
	/**
	 * coId:commemt id
	 * */
	
	public Reply updateCommentInReply(String coId,Reply reply);
	
	
	/**
	 * id:merchant
	 * */
	public List<Comment> findAllNoReply(String id);

}



