package com.oocl.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.oocl.jms.parser.MessageParser;
import com.oocl.pojo.Complaint;
import com.oocl.pojo.Merchant;
import com.oocl.service.ComplaintManager;
import com.oocl.service.MerchantManager;

public class ComplaintMessageListener implements MessageListener {
	Logger logger = Logger.getLogger(ComplaintMessageListener.class);
	private MessageParser<Complaint> parser;
	private ComplaintManager manager;
	private ServletContext context;
	
	public ComplaintMessageListener(ServletContext context) {
		this.context = context;
		initObject();
	}

	@SuppressWarnings("unchecked")
	private void initObject() {
		WebApplicationContext context = (WebApplicationContext)this.context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		manager = context.getBean(ComplaintManager.class);
		parser = (MessageParser<Complaint>) context.getBean("complaintParser");
	}

	@Override
	public void onMessage(Message message) {
		try {
			logger.info("ComplaintMessageListener receive a message "+message.getJMSMessageID());
			Complaint complaint = parser.parser(message);
			if(null!=complaint){
				manager.add(complaint);
			}
			message.acknowledge();
			logger.info("ComplaintMessageListener acknowledge the message "+message.getJMSMessageID());
		} catch (JMSException e) {
			logger.error("ComplaintMessageListener pasing message appear exeception  "+e.getMessage());
		}catch (Exception e) {
			logger.error("ComplaintMessageListener pasing message appear exeception  "+e.getMessage());
		}
	}

}
