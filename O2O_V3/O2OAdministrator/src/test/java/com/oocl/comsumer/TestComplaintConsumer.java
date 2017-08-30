package com.oocl.comsumer;


import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import com.oocl.dto.Application;
import com.oocl.dto.Protocol;
import com.oocl.pojo.Complaint;
import com.oocl.util.JSONUtil;

public class TestComplaintConsumer {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.222.29.178:61616");
		Destination queue = new ActiveMQQueue("complaint");
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(queue);
		
		Protocol protocol = new Protocol();
		Complaint complaint = new Complaint("id", "cid", "mid", "msg", new Date());
		protocol.setFlag(true);
		protocol.setObject(complaint);
		String str = JSONUtil.toJSON(protocol);
		Message message = session.createTextMessage(str);
		producer.send(message);
		session.commit();
		
		producer.close();
		session.close();
		connection.close();
	}
}
