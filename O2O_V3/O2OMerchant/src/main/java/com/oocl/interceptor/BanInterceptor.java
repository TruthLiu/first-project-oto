package com.oocl.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oocl.memory.SystemMemory;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;
import com.oocl.util.JSONUtil;
import com.oocl.util.StatusCode;

public class BanInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Merchant merchant = (Merchant)session.getAttribute("merchant");
		if(SystemMemory.isBan(merchant.getId())){
			JSONResponse<Object> resp = new JSONResponse<Object>();
			resp.setCode(StatusCode.IS_BAN);
			String json = JSONUtil.toJSON(resp);
			response.setCharacterEncoding("UTF-8");  
		    response.setContentType("application/json; charset=utf-8");
		    PrintWriter out = response.getWriter(); 
		    out.write(json);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
