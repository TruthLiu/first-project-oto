package com.oocl.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oocl.dto.ReplyContentDto;
import com.oocl.pojo.Comment;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Reply;
import com.oocl.service.CommentService;
import com.oocl.util.StatusCode;

@RequestMapping("/comment")
@Controller
public class CommentController {
	private Logger logger = Logger.getLogger(MerchantController.class);

	@Resource(name="commentServiceImpl")
	private CommentService commentService;
	
	@RequestMapping("sec/commentPage")
	public String commentPage(Model model,HttpSession session){
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		List<Comment> allCommentsList = commentService.findAllComments("m2");
//		String json = JSONUtil.toJSONC(allCommentsList);
//		logger.info(json);
		List<Comment> noReplyList = commentService.findAllNoReply("m2");
		model.addAttribute("allCommentsList", allCommentsList);
		model.addAttribute("noReplyList", noReplyList);
		return "comment";
	}
	
	@ResponseBody
	@RequestMapping("sec/getAllComments")
	public JSONResponse<Comment> getAllComments(HttpSession session){
		JSONResponse<Comment> response = new JSONResponse<Comment>();
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		List<Comment> allCommentsList = commentService.findAllComments("m2");
		response.setCode(StatusCode.SUCCESS);
		response.setResultList(allCommentsList);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("sec/getNewComments")
	public JSONResponse<Comment> getNewComments(HttpSession session){
		JSONResponse<Comment> response = new JSONResponse<Comment>();
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		List<Comment> noReplyList = commentService.findAllNoReply("m2");
		response.setCode(StatusCode.SUCCESS);
		response.setResultList(noReplyList);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="sec/reply",method=RequestMethod.POST,consumes="application/json")
	public JSONResponse<Reply> updateCommentInReply(@RequestBody ReplyContentDto dto,HttpSession session){
		JSONResponse<Reply> response = new JSONResponse<Reply>();
		Reply reply = new Reply(UUID.randomUUID().toString(), dto.getContent(), new Date());
		response.setResult(commentService.updateCommentInReply(dto.getCoId(), reply));
		response.setCode(StatusCode.SUCCESS);
		return response;
	}
	
}
