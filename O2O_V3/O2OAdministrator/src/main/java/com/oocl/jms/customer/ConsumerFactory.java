package com.oocl.jms.customer;

import java.lang.reflect.Constructor;

public class ConsumerFactory {
	private static ConsumerFactory factory;
	
	private ConsumerFactory() {
	}
	
	public static ConsumerFactory getInstance(){
		if(null==factory){
			synchronized (ConsumerFactory.class) {
				if(null==factory){
					factory = new ConsumerFactory();
				}
			}
		}
		return factory;
	}
	
	public <T> T newConsumer(Class<T> clazz, Object... args) throws Exception{
		Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
		return (T) constructor.newInstance(args);
	}
}
