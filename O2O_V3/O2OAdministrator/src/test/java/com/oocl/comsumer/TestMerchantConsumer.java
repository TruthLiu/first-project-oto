package com.oocl.comsumer;


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
import com.oocl.util.JSONUtil;

public class TestMerchantConsumer {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.222.29.178:61616");
		Destination queue = new ActiveMQQueue("o2o");
		Connection connection = factory.createConnection();
		connection.start();
		//false不开启事务，true开启事务
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(queue);
		
		Protocol protocol = new Protocol();
		//297e47945dd5a8de015dd5a948a70001
		Application application = new Application("id", "name", "unique1", "/images/head/4382c280-7024-4465-99f1-cee0295c6994.jpg", 
				"/images/idcard/94084d86-68f0-479e-a4a1-614435aae358.jpg", "软件园路444", 0, false, "");
		protocol.setFlag(true);
		protocol.setObject(application);
		String str = JSONUtil.toJSON(protocol);
		Message message = session.createTextMessage(str);
		//消息发送处于一个事务中
		producer.send(message);
		session.commit();//提交事务才会使消息发到消息服务器
		producer.close();
		session.close();
		connection.close();
	}
}
