package com.oocl.util;

import static com.oocl.util.IPPropertiesUtil.getProperty;
public class CommonURL {
	public static final String ADMINISTRATOR_URL = "http://"+getProperty("admin.ipaddr")+":"+getProperty("admin.port")+"/";
	public static final String GET_BAN_LIST = ADMINISTRATOR_URL+"O2OAdministrator/"+"getMerchant?type=black"; 
	public static final String GET_STATION = ADMINISTRATOR_URL+"O2OAdministrator/"+"approvalResult?id="; 
	public static final String GET_COMPLAINT = ADMINISTRATOR_URL+"O2OAdministrator/getComplaints";
}
