package com.oocl.dto;

public class OrderBy {
	private String field;
	private boolean asc;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	@Override
	public String toString() {
		return "OrderBy [field=" + field + ", asc=" + asc + "]";
	}
	
	
}
