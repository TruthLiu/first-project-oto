package com.oocl.jms.parser;

import javax.jms.JMSException;
import javax.jms.Message;

import com.oocl.pojo.Merchant;

public interface MessageParser<T> {
	
	public T parser(Message message) throws JMSException;
}
