package com.oocl.util;

public class MathUtil {
	
	public static String randowNumber(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<3;i++){
			int n = (int)(Math.random()*30+1);
			sb.append(n+"");
		}
		return sb.toString();
	}
}
