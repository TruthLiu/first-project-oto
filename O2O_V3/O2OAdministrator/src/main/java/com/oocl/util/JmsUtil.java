package com.oocl.util;

import java.io.IOException;
import java.util.Properties;
/**
 * 读取jms.propeties配置
 * @author MAIKE
 *
 */
public class JmsUtil {
	private static String mqUrl;
	private static String merchantQueue;
	private static String complaintQueue;
	private static String advertisementQueue;
	
	static {
		initProperties();
	}
	
	public static String getMqUrl(){
		return mqUrl;
	}

	public static String getMerchantQueue() {
		return merchantQueue;
	}

	public static String getComplaintQueue() {
		return complaintQueue;
	}

	public static String getAdvertisementQueue() {
		return advertisementQueue;
	}

	private static void initProperties() {
		Properties pro=null;
		try {
			pro = new Properties();
			pro.load(DBUtil.class.getClassLoader().getResourceAsStream("jms.properties"));
			mqUrl = pro.getProperty("jms.url");
			merchantQueue = pro.getProperty("jms.merchantQueue");
			advertisementQueue = pro.getProperty("jms.advertisementQueue");
			complaintQueue = pro.getProperty("jms.complaintQueue");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
