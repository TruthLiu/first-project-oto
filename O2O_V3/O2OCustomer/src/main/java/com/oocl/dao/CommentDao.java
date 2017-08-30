package com.oocl.dao;

import java.util.List;
import java.util.Set;

import com.oocl.pojo.Comment;

public interface CommentDao {
	public Set<Comment> findCommentByMId(String mid);
	public Comment addComment(Comment comment,String orderId);
}
