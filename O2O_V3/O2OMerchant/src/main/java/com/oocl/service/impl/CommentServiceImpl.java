package com.oocl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.CommentDao;
import com.oocl.pojo.Comment;
import com.oocl.pojo.Reply;
import com.oocl.service.CommentService;

@Service(value="commentServiceImpl")
public class CommentServiceImpl implements CommentService{
	
	@Resource(name="commentDaoImpl")
	private CommentDao commentDao;

	@Override
	public List<Comment> findAllComments(String id) {
		return commentDao.findAllComments(id);
	}

	@Override
	@Transactional
	public Reply updateCommentInReply(String coId, Reply reply) {
		return commentDao.updateCommentInReply(coId, reply);
	}

	@Override
	public List<Comment> findAllNoReply(String id) {
		return commentDao.findAllNoReply(id);
	}

	@Override
	@Transactional
	public Comment addComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.addComment(comment);
	}
	
	



}
