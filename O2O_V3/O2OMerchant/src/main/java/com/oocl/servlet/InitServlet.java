package com.oocl.servlet;

import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.oocl.memory.SystemMemory;
import com.oocl.pojo.Protocol;
import com.oocl.pojo.State;
import com.oocl.util.CommonURL;
import com.oocl.util.HTTPConnUtil;

@Component
public class InitServlet implements InitializingBean{

	private Logger logger = Logger.getLogger(InitServlet.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("init...................");
		Protocol protocol = HTTPConnUtil.sendGet(CommonURL.GET_BAN_LIST);
		if (protocol!=null) {
			logger.info("protocol not null");
			Set<String> banSet  = (Set) protocol.getSet();
			SystemMemory.banSet = banSet;
		}
	}

}
