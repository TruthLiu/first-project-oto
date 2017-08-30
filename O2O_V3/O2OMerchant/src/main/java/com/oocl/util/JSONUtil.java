package com.oocl.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.dto.Complaint;
import com.oocl.pojo.Comment;
import com.oocl.pojo.Dish;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;
import com.oocl.pojo.Protocol;
import com.oocl.pojo.SocketOrder;

public class JSONUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String toJSON(Object object){
		String result = null;
		try {
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Parse a protocol to JSON String
	 * @param protocol
	 * @return string
	 */
	public static String toJSON(Protocol protocol){
		String result = null;
		try {
			result = mapper.writeValueAsString(protocol);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Parse a Merchant to JSON String
	 * @param merchant
	 * @return string
	 */
	public static String toJSON(Merchant merchant){
		String result = null;
		try {
			result = mapper.writeValueAsString(merchant);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Parse a List<Dish> to JSON String
	 * @param merchant
	 * @return string
	 */
	public static String toJSON(List<Dish> list){
		String result = null;
		try {
			result = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Parse a List<Dish> to JSON String
	 * @param merchant
	 * @return string
	 */
	public static String toJSONC(List<Comment> list){
		String result = null;
		try {
			result = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Parse a Merchant to JSON String
	 * @param merchant
	 * @return string
	 */
	public static String toJSON(JSONResponse<Object> resp){
		String result = null;
		try {
			result = mapper.writeValueAsString(resp);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Parse a json String to Protocol class
	 * @param json
	 * @return
	 */
	public static Protocol toProtocol(String json){
		Protocol protocol = null;
		try {
			protocol = mapper.readValue(json, Protocol.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return protocol;
	}
	
	public static SocketOrder toSocketOrder(String json){
		SocketOrder socketOrder = null;
		try {
			socketOrder = mapper.readValue(json, SocketOrder.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socketOrder;
	}
	
	public static Complaint toComplaint(String json){
		Complaint complaint = null;
		try {
			complaint = mapper.readValue(json, Complaint.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return complaint;
	}
}
