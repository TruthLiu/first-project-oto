package com.oocl.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.oocl.jms.parser.MessageParser;
import com.oocl.pojo.Advertisement;
import com.oocl.pojo.Complaint;
import com.oocl.pojo.Merchant;
import com.oocl.service.AdvertisementManager;
import com.oocl.service.ComplaintManager;
import com.oocl.service.MerchantManager;

public class AdvertisementMessageListener implements MessageListener {
	Logger logger = Logger.getLogger(AdvertisementMessageListener.class);
	private MessageParser<Advertisement> parser;
	private AdvertisementManager manager;
	private ServletContext context;
	
	public AdvertisementMessageListener(ServletContext context) {
		this.context = context;
		initObject();
	}

	@SuppressWarnings("unchecked")
	private void initObject() {
		//获取spring容器对象
		WebApplicationContext context = (WebApplicationContext)this.context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		manager = context.getBean(AdvertisementManager.class);
		parser = (MessageParser<Advertisement>) context.getBean("advertisementParser");
	}

	@Override
	public void onMessage(Message message) {
		try {
			logger.info("AdvertisementMessageListener receive a message "+message.getJMSMessageID());
			Advertisement advertisement = parser.parser(message);
			if(null!=advertisement){
				manager.add(advertisement);
			}
			message.acknowledge();
			logger.info("AdvertisementMessageListener acknowledge the message "+message.getJMSMessageID());
		} catch (JMSException e) {
			logger.error("AdvertisementMessageListener pasing message appear exeception  "+e.getMessage());
		}catch (Exception e) {
			logger.error("AdvertisementMessageListener pasing message appear exeception  "+e.getMessage());
		}
	}

}
