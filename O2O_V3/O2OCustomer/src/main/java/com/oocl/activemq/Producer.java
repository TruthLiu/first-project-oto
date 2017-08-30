package com.oocl.activemq;

import javax.jms.Connection;



public interface Producer {
	public void sendMsgToJMS(String str);
	public Connection getJMSConnection();
}
