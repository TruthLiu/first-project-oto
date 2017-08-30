package com.oocl.service.impl;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oocl.pojo.Comment;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Reply;
import com.oocl.service.CommentService;

public class CommentServiceImplTest {
	private static ApplicationContext context;
	private static CommentService commentService;
	
	@BeforeClass
	public static void init(){
		context = new ClassPathXmlApplicationContext("classpath:xml-config/applicationContext.xml");
		commentService = (CommentService) context.getBean("commentServiceImpl");
	}
	
	@Test
	public void testAddComments() {
		//co1
		Comment comment1 = new Comment();
		comment1.setId("1111");
		comment1.setContent("nihaozzzzz");
		comment1.setCustomer(null);
		comment1.setOrder(null);
		Merchant merchant1 = new Merchant();
		merchant1.setId("2222");
		merchant1.setmAccount("aaa");
		comment1.setMerchant(merchant1);
		Reply reply1 = new Reply();
		reply1.setId("3333");
		comment1.setReply(reply1);
		comment1.setStar(5.0);
		commentService.addComment(comment1);
		
		//co3
		Comment comment3 = new Comment();
		comment3.setId("5555");
		comment3.setContent("nihaoz32323");
		comment3.setCustomer(null);
		comment3.setOrder(null);
		
		comment3.setMerchant(merchant1);
		comment3.setReply(null);
		comment3.setStar(5.0);
		commentService.addComment(comment3);
		
		
		//co3
		Comment comment2 = new Comment();
		comment2.setId("4444");
		comment2.setContent("nihaozzfdfzzz");
		comment2.setCustomer(null);
		comment2.setOrder(null);
		Merchant merchant2 = new Merchant();
		merchant2.setId("2222");
		merchant2.setmAccount("bbb");
		comment2.setMerchant(merchant2);
		comment2.setReply(null);
		comment2.setStar(5.0);
		commentService.addComment(comment2);
		
	}

	@Test
	public void testFindAllComments() {
		List<Comment> list = commentService.findAllComments("m2");
		Assert.assertTrue(list.size()==2);
	}

	@Test
	public void testUpdateCommentInReply() {
		Reply reply = new Reply();
		reply.setId("re2");
		commentService.updateCommentInReply("co5", reply);
	}

	@Test
	public void testFindAllNoReply() {
		List<Comment> list = commentService.findAllNoReply("m2");
		Assert.assertTrue(list.size()==0);
	}

}
