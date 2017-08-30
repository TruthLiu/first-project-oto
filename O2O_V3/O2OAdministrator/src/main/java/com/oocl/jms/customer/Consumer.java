package com.oocl.jms.customer;

import javax.jms.JMSException;
import javax.jms.MessageListener;

public interface Consumer {
	/**
	 * receive message
	 * @param listener
	 * @throws JMSException
	 */
	public void receive(MessageListener listener) throws JMSException;
	/**
	 * close consumer
	 * @throws JMSException
	 */
	public void close() throws JMSException;
	/**
	 * commit transaction
	 */
	public void commit() throws JMSException;
	
}
