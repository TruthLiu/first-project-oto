package com.oocl.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class RequestUtil {
	
	public static JSONObject parseJsonParameters(HttpServletRequest request) throws IOException{
		InputStream in = request.getInputStream();
		byte[] buf = new byte[64];
		int n=0;
		StringBuilder sb = new StringBuilder();
		while((n=in.read(buf))!=-1){
			sb.append(new String(buf,0,n));
		}
		return new JSONObject(sb.toString());
	}
	
	public static void sendResponse(boolean flag,Object obj,HttpServletResponse response) throws IOException{
		JSONObject object = new JSONObject();
		if(!flag){
			object.put("flag", false);
			object.put("msg", obj.toString());
		}else {
			object.put("flag", true);
			object.put("data", obj);
		}
		response.getWriter().write(object.toString());
		response.getWriter().close();
	}
}
