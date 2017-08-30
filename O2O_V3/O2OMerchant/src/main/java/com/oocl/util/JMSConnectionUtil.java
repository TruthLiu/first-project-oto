package com.oocl.util;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConnectionUtil {
	private static ConnectionFactory factory = null;
	private static Connection connection = null;
	
	public static Connection getConnection(String tcp){
		if (connection==null) {
			synchronized (JMSConnectionUtil.class) {
				if (connection==null) {
					factory = new ActiveMQConnectionFactory(tcp);
					try {
						connection = factory.createConnection();
						connection.start();
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return connection;
	}
	
}
