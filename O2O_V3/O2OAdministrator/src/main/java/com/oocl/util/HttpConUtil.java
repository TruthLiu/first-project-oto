package com.oocl.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.oocl.dto.Protocol;

public class HttpConUtil {
	private static Logger logger = Logger.getLogger(HttpConUtil.class);
	
	@SuppressWarnings("rawtypes")
	public static Protocol sendPost(String urlAddr,Protocol protocol){
		HttpURLConnection hconn = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			String msg = JSONUtil.toJSON(protocol);
			String resg = "protocol="+msg;
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
			return JSONUtil.toProtocol(new String(buf));
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
	
	public static void sendBanState(String address){
//		HttpURLConnection hconn = null;
//		try {
//			URL url = new URL(address);
//			URLConnection conn = url.openConnection();
//			hconn = (HttpURLConnection) conn;
//			hconn.setRequestMethod("GET");
//			logger.info("send ban state to Merchant server");
//		} catch (IOException e) {
//			e.printStackTrace();
//			logger.error(e);
//		} finally {
//			if (hconn != null) {
//				hconn.disconnect();
//			}
//		}
		HttpURLConnection hconn = null;
		InputStream in = null;
		String response = "";
		try {
			URL url = new URL(address);
			URLConnection conn = url.openConnection();
			hconn = (HttpURLConnection) conn;
			hconn.setRequestMethod("GET");
			in = hconn.getInputStream();
			int len = hconn.getContentLength();
			byte[] buffer = new byte[len];
			in.read(buffer);
			response = new String(buffer, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (hconn != null) {
				hconn.disconnect();
			}
		}
		
		System.out.println(response);
	}

}
