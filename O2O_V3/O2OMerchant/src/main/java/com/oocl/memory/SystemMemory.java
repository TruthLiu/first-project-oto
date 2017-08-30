package com.oocl.memory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SystemMemory {

	//存储拉黑商家ID
	public static Set<String> banSet = new HashSet<String>();
	
	
	public static void addBanSets(List<String> list){
		for (String string : list) {
			banSet.add(string);
		}
	}
	
	public static synchronized void addBanSet(String mId){
		banSet.add(mId);
	}
	
	public static synchronized void removeBanSet(String mId){
		banSet.remove(mId);
	}
	
	public static Set<String> getBanSet(){
		return banSet;
	}
	/**
	 * black:true,
	 * white:false
	 * */
	public static boolean isBan(String mId){
		if (banSet==null) {
			return false;
		}
		for (String str : banSet) {
			if (str.equals(mId)) {
				return true;
			}
		}
		return false;
	}
	
}
