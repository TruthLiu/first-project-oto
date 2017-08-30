package com.oocl.activemq.impl;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Component;

import com.oocl.activemq.Producer;
import com.oocl.util.JMSConnectionUtil;
import static com.oocl.util.IPPropertiesUtil.getProperty;
@Component(value="applicationProducer")
public class ApplicationProducer implements Producer {
	private Destination queue = null;
	private Connection connection = null;
	private Session session = null;
	private MessageProducer producer = null;
	private String tcp=getProperty("jms.tcp");
	public ApplicationProducer() {
		queue = new ActiveMQQueue("o2o");
		connection = JMSConnectionUtil.getConnection(tcp);
	}

	public void sendMsgToJMS(String str) {
		try {
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(queue);
			TextMessage msg = session.createTextMessage(str);
			producer.send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
				producer.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Connection getJMSConnection() {
		return connection;
	}

}
