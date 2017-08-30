package com.oocl.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.dto.Protocol;

public class JSONUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	
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
	
	public static String toJSON(Object obj){
		String result = null;
		try {
			result = mapper.writeValueAsString(obj);
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

}
