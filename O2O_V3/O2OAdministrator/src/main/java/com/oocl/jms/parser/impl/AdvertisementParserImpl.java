package com.oocl.jms.parser.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.oocl.dto.Protocol;
import com.oocl.jms.parser.MessageParser;
import com.oocl.pojo.Advertisement;
import com.oocl.pojo.Complaint;
import com.oocl.util.JSONUtil;

@Component("advertisementParser")
public class AdvertisementParserImpl implements MessageParser<Advertisement>{

	@SuppressWarnings("unchecked")
	@Override
	public Advertisement parser(Message message) throws JMSException{
		TextMessage msg = (TextMessage) message;
		Protocol protocol = JSONUtil.toProtocol(msg.getText());
		if(protocol.isFlag()){
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) protocol.getObject();
			Advertisement advertisement = toAdvertisement(map);
			return advertisement;
		}
		return null;
	}
	
	private Advertisement toAdvertisement(LinkedHashMap<String, Object> map) {
		Advertisement advertisement = new Advertisement();
		advertisement.setId((String) map.get("id"));
		advertisement.setMid((String) map.get("mid"));
		advertisement.setDid((String) map.get("did"));
		long createTime = (long) map.get("createTime");
		advertisement.setCreateTime(new Date(createTime));
		advertisement.setImg((String) map.get("img"));
		advertisement.setPrice((double) map.get("price"));
		advertisement.setRecommend((int) map.get("recommend"));
		advertisement.setStatus((int) map.get("status"));
		return advertisement;
	}

}
