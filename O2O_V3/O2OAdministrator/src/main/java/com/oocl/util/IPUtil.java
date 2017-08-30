package com.oocl.util;

import java.io.IOException;
import java.util.Properties;

public class IPUtil {
	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(IPUtil.class.getClassLoader().getResourceAsStream("ip.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

}
