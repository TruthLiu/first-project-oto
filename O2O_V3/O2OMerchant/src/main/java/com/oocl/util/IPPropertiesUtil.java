package com.oocl.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class IPPropertiesUtil {
	
	private static final Logger LOGGER = Logger.getLogger(IPPropertiesUtil.class);
	
	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(IPPropertiesUtil.class.getClassLoader().getResourceAsStream("ip.properties"));
		} catch (IOException e) {
			LOGGER.error("", e);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
