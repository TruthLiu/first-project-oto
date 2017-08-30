package com.oocl.dto;

public class Process {
	private String id;
	private String result;
	private String bContent;

	public Process(){
		super();
	}

	public Process(String id, String result, String bContent) {
		super();
		this.id = id;
		this.result = result;
		this.bContent = bContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	
	
}
