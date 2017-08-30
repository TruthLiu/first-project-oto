package com.oocl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oocl.dao.CommentDao;
import com.oocl.pojo.Comment;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Reply;


@Repository(value="commentDaoImpl")
public class CommentDaoImpl implements CommentDao{
	
	private Logger logger = Logger.getLogger(CommentDaoImpl.class);
	@PersistenceContext(name="punit")
	private EntityManager em;

	@Override
	public List<Comment> findAllComments(String id) {
		String jpql="select co from Comment co where co.merchant.id=:id";
		List<Comment> ls=em.createQuery(jpql)
				.setParameter("id", id)
				.getResultList();
		return ls;
	}

	@Override
	public Reply updateCommentInReply(String coId,Reply reply) {
		Comment comment = em.find(Comment.class, coId);
		reply.setComment(comment);
		em.persist(reply);
		return reply;
	}

	@Override
	public List<Comment> findAllNoReply(String id) {
		String jpql="select co from Comment co where co.merchant.id=:id and co.reply is empty";
		List<Comment> ls=em.createQuery(jpql)
				.setParameter("id", id)
				.getResultList();
		return ls;
	}

	@Override
	public Comment addComment(Comment comment) {
		em.persist(comment);
		return comment;
	}

}
