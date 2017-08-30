package com.oocl.jms.parser.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.oocl.dto.Protocol;
import com.oocl.jms.parser.MessageParser;
import com.oocl.pojo.Complaint;
import com.oocl.util.JSONUtil;

@Component("complaintParser")
public class ComplaintParserImpl implements MessageParser<Complaint>{

	@SuppressWarnings("unchecked")
	@Override
	public Complaint parser(Message message) throws JMSException{
		TextMessage msg = (TextMessage) message;
		Protocol protocol = JSONUtil.toProtocol(msg.getText());
		if(protocol.isFlag()){
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) protocol.getObject();
			Complaint complaint = toComplaint(map);
			return complaint;
		}
		return null;
	}
	
	private Complaint toComplaint(LinkedHashMap<String, Object> map) {
		Complaint complaint = new Complaint();
		complaint.setId((String) map.get("id"));
		complaint.setMid((String) map.get("mid"));
		complaint.setCid((String) map.get("cid"));
		complaint.setMsg((String) map.get("msg"));
		long time = (long) map.get("time");
		complaint.setTime(new Date(time));
		return complaint;
	}

}
