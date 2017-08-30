package com.oocl.jms.customer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.Logger;

public class AdvertisementConsumer implements Consumer{
	Logger logger = Logger.getLogger(AdvertisementConsumer.class);
	private Connection connection;
	private String queueName;
	private Session session;
	private MessageConsumer consumer;
	private String mqUrl;
	
	public AdvertisementConsumer(String queueName,String mqUrl) {
		this.queueName = queueName;
		this.mqUrl = mqUrl;
		initConsumer();
	}

	private void initConsumer() {
		try {
			Destination queue = new ActiveMQQueue(queueName);
			connection = JmsConnection.getInstance(mqUrl);
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			consumer = session.createConsumer(queue);
		} catch (JMSException e) {
			logger.error("Init AdvertisementConsumer appear exeception  "+e.getMessage());
		}
	}

	@Override
	public void receive(MessageListener listener) throws JMSException {
		consumer.setMessageListener(listener);
	}

	@Override
	public void close() {
		try {
			if(null!=consumer){
				consumer.close();
			}
			if(null!=session){
				session.close();
			}
			if(null!=connection){
				connection.close();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commit() throws JMSException {
		session.commit();
	}

}
