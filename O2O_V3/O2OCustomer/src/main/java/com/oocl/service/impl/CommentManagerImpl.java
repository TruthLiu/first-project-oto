package com.oocl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.dao.CommentDao;
import com.oocl.dao.CustomerDao;
import com.oocl.pojo.Comment;
import com.oocl.service.CommentManager;
import com.oocl.util.VOUtil;
import com.oocl.vo.CommentVO;

@Service("commentManagerImpl")
public class CommentManagerImpl implements CommentManager {

	@Resource(name = "commentDaoImpl")
	private CommentDao commentDao;
	
	@Override
	@Transactional
	public List<CommentVO> findCommentByMId(String MId) {
		CommentVO commentVO;
		List<CommentVO> commentVOList = new ArrayList<CommentVO>();
		for(Comment comment:commentDao.findCommentByMId(MId)){
			commentVOList.add(VOUtil.CommentPOToVO(comment));
		}
		return commentVOList;
	}

	@Override
	@Transactional
	public Comment addComment(Comment comment, String orderId) {
		return commentDao.addComment(comment, orderId);
	}

}
