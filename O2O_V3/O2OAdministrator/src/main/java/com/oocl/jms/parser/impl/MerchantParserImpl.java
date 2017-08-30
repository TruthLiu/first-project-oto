package com.oocl.jms.parser.impl;

import java.util.LinkedHashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.oocl.constant.ApplyStatus;
import com.oocl.dto.Application;
import com.oocl.dto.Protocol;
import com.oocl.jms.parser.MessageParser;
import com.oocl.pojo.Merchant;
import com.oocl.util.JSONUtil;

@Component("merchantParser")
public class MerchantParserImpl implements MessageParser<Merchant>{

	@SuppressWarnings("unchecked")
	@Override
	public Merchant parser(Message message) throws JMSException{
		TextMessage msg = (TextMessage) message;
		Protocol protocol = JSONUtil.toProtocol(msg.getText());
		if(protocol.isFlag()){
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) protocol.getObject();
			Application app = toApplication(map);
			Merchant merchant = toMerchant(app);
			return merchant;
		}
		return null;
	}
	
	private Merchant toMerchant(Application app){
		Merchant merchant = new Merchant();
		merchant.setId(app.getId());
		merchant.setName(app.getmAccount());
		merchant.setAddress(app.getAddress());
		merchant.setShopName(app.getmName());
		merchant.setIdcard(app.getImgCard());
		merchant.setHeading(app.getImgHead());
		merchant.setBan(false);
		merchant.setStatus(ApplyStatus.PENGDING);
			
		return merchant;
	}

	private Application toApplication(LinkedHashMap<String, Object> map) {
		Application app = new Application();
		app.setId((String)map.get("id"));
		app.setmAccount((String)map.get("mAccount"));
		app.setmName((String)map.get("mName"));
		app.setImgCard((String)map.get("imgCard"));
		app.setImgHead((String)map.get("imgCard"));
		app.setAddress((String)map.get("address"));
		app.setStatus(ApplyStatus.PENGDING);
		app.setIsBan(false);
		return app;
	}

}
