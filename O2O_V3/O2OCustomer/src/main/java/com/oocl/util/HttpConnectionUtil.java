package com.oocl.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.dto.RecommendDto;
import com.oocl.pojo.ASideResponse;
import com.oocl.pojo.Advertisement;

public class HttpConnectionUtil {
	private static final Logger LOGGER = Logger.getLogger(HttpConnectionUtil.class);

	public static String sendGet(String address) {
		HttpURLConnection hconn = null;
		InputStream in = null;
		String response = null;
		
		try {
			URL url = new URL(address);
			URLConnection conn = url.openConnection();
			hconn = (HttpURLConnection) conn;
			
			hconn.setRequestMethod("GET");
//			hconn.setRequestProperty("Accept-Encoding", "identity");
			
			in = hconn.getInputStream();
//			int len = hconn.getContentLength();
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			
			response = new String(buffer, "utf-8");
		} catch (NegativeArraySizeException | IOException e) {
			LOGGER.debug("", e);
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
		
		return response;
	}
	
	public static ASideResponse<String> getASideResponse(String json) {
		ObjectMapper mapper = new ObjectMapper();
		ASideResponse<String> result = null;
		try {
			result = mapper.readValue(json, new TypeReference<ASideResponse<String>>() {});
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		return result;
	}
	
	
	public static ASideResponse<RecommendDto> getASideRecommendResponse(String json) {
		ObjectMapper mapper = new ObjectMapper();
		ASideResponse<RecommendDto> result = null;
		try {
			result = mapper.readValue(json, new TypeReference<ASideResponse<RecommendDto>>() {});
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		return result;
	}
}
