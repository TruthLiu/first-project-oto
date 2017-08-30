package com.oocl.activemq.impl;

import com.oocl.activemq.Producer;

public class ProducerFactory {
	private static Producer appProducer;
	public static Producer getAppProInstance(){
		if (appProducer==null) {
			synchronized (Producer.class) {
				if (appProducer==null) {
					appProducer = new ApplicationProducer();
				}
			}
		}
		return appProducer;
	}
}
