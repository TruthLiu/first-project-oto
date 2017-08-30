package com.oocl.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.oocl.jms.parser.MessageParser;
import com.oocl.pojo.Merchant;
import com.oocl.service.MerchantManager;

public class MerchantMessageListener implements MessageListener {
	Logger logger = Logger.getLogger(MerchantMessageListener.class);
	private MessageParser<Merchant> parser;
	private MerchantManager manager;
	private ServletContext context;
	
	public MerchantMessageListener(ServletContext context) {
		this.context = context;
		initObject();
	}

	@SuppressWarnings("unchecked")
	private void initObject() {
		//获取spring容器对象
		WebApplicationContext context = (WebApplicationContext)this.context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		manager = context.getBean(MerchantManager.class);
		parser = (MessageParser<Merchant>) context.getBean("merchantParser");
	}

	@Override
	public void onMessage(Message message) {
		try {
			logger.info("MerchantMessageListener receive a message "+message.getJMSMessageID());
			Merchant merchant = parser.parser(message);
			if(null!=merchant){
				Merchant m1 = manager.findMerchantById(merchant.getId());
				if(null==m1){
					manager.add(merchant);
				}else {
					manager.update(merchant);
				}
				Object obj = context.getAttribute("pendingCount");
				if(obj==null){
					context.setAttribute("pendingCount",1);
				}else {
					int count = ((Integer)context.getAttribute("pendingCount")).intValue();
					context.setAttribute("pendingCount",++count);
				}
			}
			message.acknowledge();
			logger.info("MerchantMessageListener acknowledge the message "+message.getJMSMessageID());
		} catch (JMSException e) {
			logger.error("MerchantMessageListener pasing message appear exeception  "+e.getMessage());
		}catch (Exception e) {
			logger.error("MerchantMessageListener pasing message appear exeception  "+e.getMessage());
		}
	}

}
