package com.oocl.pojo;

public class State {
	private Integer state;
	private String content;
	public State(Integer state, String content) {
		super();
		this.state = state;
		this.content = content;
	}
	public State() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
