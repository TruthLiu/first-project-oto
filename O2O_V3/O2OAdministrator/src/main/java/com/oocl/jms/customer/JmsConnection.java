package com.oocl.jms.customer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsConnection {
	private static Connection connection;
	
	public static Connection getInstance(String mqUrl){
		if(null==connection){
			synchronized (JmsConnection.class) {
				try {
					ConnectionFactory factory = new ActiveMQConnectionFactory(mqUrl);
					connection = factory.createConnection();
					connection.start();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		return connection;
	}
}
