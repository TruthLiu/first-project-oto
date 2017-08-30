package com.oocl.dto;

import java.util.List;
import java.util.Map;

public class QueryParam {
	//and参数
	private Map<String, Object> andParams;
	//排序
	private List<OrderBy> orderParams;
	//分页
	private int startIndex;
	private int maxCounts;
	public Map<String, Object> getAndParams() {
		return andParams;
	}
	public void setAndParams(Map<String, Object> andParams) {
		this.andParams = andParams;
	}
	public List<OrderBy> getOrderParams() {
		return orderParams;
	}
	public void setOrderParams(List<OrderBy> orderParams) {
		this.orderParams = orderParams;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getMaxCounts() {
		return maxCounts;
	}
	public void setMaxCounts(int maxCounts) {
		this.maxCounts = maxCounts;
	}
	@Override
	public String toString() {
		return "QueryParam [andParams=" + andParams + ", orderParams="
				+ orderParams + ", startIndex=" + startIndex + ", maxCounts="
				+ maxCounts + "]";
	}
	
	
	
}
