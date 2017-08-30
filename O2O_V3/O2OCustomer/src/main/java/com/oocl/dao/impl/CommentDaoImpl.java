package com.oocl.dao.impl;


import java.util.Set;







import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.oocl.dao.CommentDao;
import com.oocl.pojo.Comment;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Order;

@Repository(value = "commentDaoImpl")
public class CommentDaoImpl implements CommentDao{

	@PersistenceContext(name="punit")
	private EntityManager entitymanager;
	
	@Override
	public Set<Comment> findCommentByMId(String mid) {
		return entitymanager.find(Merchant.class, mid).getComment();
	}

	@Override
	public Comment addComment(Comment comment,String orderId) {
		Order order = entitymanager.find(Order.class, orderId);
		if(order.getComment()!=null)return null;
		else{
			comment.setOrder(order);
			comment.setCustomer(order.getCustomer());
			comment.setMerchant(order.getMerchant());
			entitymanager.persist(comment);
			return comment;
		}
	}

}
