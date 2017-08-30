package com.oocl.listener;

import javax.jms.JMSException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.oocl.jms.customer.AdvertisementConsumer;
import com.oocl.jms.customer.ComplaintConsumer;
import com.oocl.jms.customer.Consumer;
import com.oocl.jms.customer.MerchantConsumer;
import com.oocl.jms.customer.ConsumerFactory;
import com.oocl.jms.listener.AdvertisementMessageListener;
import com.oocl.jms.listener.ComplaintMessageListener;
import com.oocl.jms.listener.MerchantMessageListener;
import com.oocl.util.JmsUtil;

@WebListener
public class SystemListener implements ServletContextListener {
	Logger logger = Logger.getLogger(SystemListener.class);
	Consumer merchantConsumer = null;
	Consumer complaintConsumer = null;
	Consumer advertisementConsumer = null;

    public void contextInitialized(ServletContextEvent sce) {
    	try {
    		logger.info("MerchantMessageListener is started");
    		merchantConsumer = ConsumerFactory.getInstance().newConsumer(MerchantConsumer.class,JmsUtil.getMerchantQueue(),JmsUtil.getMqUrl());
    		merchantConsumer.receive(new MerchantMessageListener(sce.getServletContext()));
		} catch (JMSException e) {
			logger.error("MerchantMessageListener starting appear exeception  "+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	try {
    		logger.info("ComplaintMessageListener is started");
    		complaintConsumer = ConsumerFactory.getInstance().newConsumer(ComplaintConsumer.class, JmsUtil.getComplaintQueue(),JmsUtil.getMqUrl());
    		complaintConsumer.receive(new ComplaintMessageListener(sce.getServletContext()));
		} catch (JMSException e) {
			logger.error("ComplaintMessageListener starting appear exeception  "+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	try {
    		logger.info("AdvertisementMessageListener is started");
    		advertisementConsumer = ConsumerFactory.getInstance().newConsumer(AdvertisementConsumer.class, JmsUtil.getAdvertisementQueue(), JmsUtil.getMqUrl());
    		advertisementConsumer.receive(new AdvertisementMessageListener(sce.getServletContext()));
		} catch (JMSException e) {
			logger.error("AdvertisementMessageListener starting appear exeception  "+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


    public void contextDestroyed(ServletContextEvent sce) {
        if(null!=merchantConsumer){
        	logger.info("MerchantMessageListener is closed");
        	try {
        		merchantConsumer.close();
			} catch (JMSException e) {
				logger.error("MerchantMessageListener closing appear exeception  "+e.getMessage());
			}
        }
        
        if(null!=complaintConsumer){
        	logger.info("ComplaintMessageListener is closed");
        	try {
        		complaintConsumer.close();
			} catch (JMSException e) {
				logger.error("ComplaintMessageListener closing appear exeception  "+e.getMessage());
			}
        }
        
        if(null!=advertisementConsumer){
        	logger.info("AdvertisementMessageListener is closed");
        	try {
        		advertisementConsumer.close();
			} catch (JMSException e) {
				logger.error("AdvertisementMessageListener closing appear exeception  "+e.getMessage());
			}
        }
    }
	
}
