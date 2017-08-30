package com.oocl.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.dto.ComplaintSearchCondition;
import com.oocl.pojo.Protocol;

public class HTTPConnUtil {
	
	private static final Logger LOGGER = Logger.getLogger(HTTPConnUtil.class);

	private static  boolean isRun = true; 
	
	public static Protocol sendGet(String address) {
		LOGGER.info("requestUrl:"+address);
		Protocol protocol =null;
		HttpURLConnection hconn = null;
		InputStream in = null;
		String response = "";
		try {
			URL url = new URL(address);
			URLConnection conn = url.openConnection();
			hconn = (HttpURLConnection) conn;
			hconn.setRequestMethod("GET");
			
			in = hconn.getInputStream();
			int length = in.available();
			int len = hconn.getContentLength();
			
			byte[] buffer = new byte[length];
			
			in.read(buffer);
			response = new String(buffer, "utf-8");
			protocol = JSONUtil.toProtocol(response);
		} catch (IOException e) {
			LOGGER.info("server down", e);
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOGGER.debug("", e);
				}
			}
			if (hconn != null) {
				hconn.disconnect();
			}
		}
		
		LOGGER.info("response:"+response);
		return protocol;
	}
	
	
	public static JSONObject sendPost(String urlAddr,ComplaintSearchCondition protocol){
		HttpURLConnection hconn = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			String msg = JSONUtil.toJSON(protocol);
			String resg = "condition="+msg;
			System.out.println(msg);
			URL url = new URL(urlAddr);
			hconn = (HttpURLConnection)url.openConnection();
			hconn.setRequestMethod("POST");
			hconn.setDoOutput(true);
			out = hconn.getOutputStream();
			out.write(resg.getBytes());
			out.flush();
		
			in = hconn.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			return new JSONObject(new String(buf));
//			return JSONUtil.toProtocol();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null!=in){
					in.close();
				}
				if(null!=out){
					out.close();
				}
				if(null!=hconn){
					hconn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	

	public static void setRun(boolean isRun) {
		HTTPConnUtil.isRun = isRun;
	}

	public static boolean isRun() {
		return isRun;
	}

	
}
