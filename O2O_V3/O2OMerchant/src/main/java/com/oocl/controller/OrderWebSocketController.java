package com.oocl.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.oocl.interceptor.WebSocketHandlerInterceptor;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.SocketOrder;
import com.oocl.util.JSONUtil;

@Controller(value="orderWebSocketController")
@RequestMapping("/websocket/order")
public class OrderWebSocketController extends TextWebSocketHandler{
	private Map<String,WebSocketSession> sessionsMerchant = new HashMap<String, WebSocketSession>(); 
	private Map<String,WebSocketSession> sessionsCustomer = new HashMap<String, WebSocketSession>(); 
	private Logger logger = Logger.getLogger(OrderWebSocketController.class);
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		
		logger.info("text message: " + session.getId() + "-" + message.getPayload());
//		session.sendMessage(message);
//		session.sendMessage(new TextMessage("hello world!"));
		if (message.getPayload()!=null&&!message.getPayload().equals("")) {
			SocketOrder socketOrder = JSONUtil.toSocketOrder(message.getPayload());
			if (socketOrder.getcId()!=null&&!socketOrder.getcId().equals("")) {
				sessionsCustomer.put(socketOrder.getcId(), session);
				logger.info("customer num"+sessionsCustomer.size());
				sessionsMerchant.get(socketOrder.getmId()).sendMessage(message);
			}
		}
		
		
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		Map<String, Object> map = session.getAttributes();
		Merchant merchant =(Merchant) map.get("merchant");
		if (merchant!=null) {
			sessionsMerchant.put(merchant.getId(), session);
		}
		
		logger.info("merchant num:"+sessionsMerchant.size());
		
//		String cId = (String)map.get("cId");
//		String mId = (String)map.get("mId");
//		String cName = (String)map.get("cName");
//		sessions.put(cId, session);
//		logger.info("OrderWebSocketController----cId:"+cId+",cName:"+cName+",mId:"+mId);
	}
	
	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		super.handleTransportError(session, exception);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
	
	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return super.supportsPartialMessages();
	}
}
