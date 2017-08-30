package com.oocl.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.oocl.pojo.Customer;
import com.oocl.pojo.Merchant;

public class WebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor{

	private Logger logger = Logger.getLogger(WebSocketHandlerInterceptor.class);
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		logger.info("beforeHandShake...........");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession();
			if (session!=null) {
				Merchant merchant = (Merchant)session.getAttribute("merchant");
				if(merchant!=null){
					attributes.put("merchant", merchant);
					logger.info("WebSocketHandlerInterceptor----merchant-account:"+merchant.getmAccount());
				}
				
				if(session.getAttribute("loginCustomer")==null){
					logger.info("customer null");
				}
				
//				attributes.put("cName", cName);
//				attributes.put("mId", mId);
			}
		}
		
		
		return true;
	}
	
	
	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		logger.info("afterHandshake...........");
		super.afterHandshake(request, response, wsHandler, ex);
	}
	
}
