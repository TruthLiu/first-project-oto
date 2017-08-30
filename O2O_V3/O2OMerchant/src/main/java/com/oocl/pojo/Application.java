package com.oocl.pojo;

public class Application {
	/**
	 * User Id
	 * */
	private String id;
	/**
	 * User Name
	 * */
	private String mName;
	/**
	 * User mAccount
	 * */
	private String mAccount;
	/**
	 * User ID Card
	 * */
	private String imgCard;
	/**
	 * User Head Image
	 * */
	private String imgHead;
	/**
	 * User Address
	 * */
	private String address;
	/**
	 * Access Status 0:waiting check ; 1:access ;2:refuse
	 * */
	private Integer status;
	/**
	 * isBan true:yes ; false:no
	 * */
	private Boolean isBan;
	/**
	 * feedback
	 * */
	private String content;
	
	
	
	
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Application(String id, String mName, String mAccount,
			String imgCard, String imgHead, String address, Integer status,
			Boolean isBan, String content) {
		super();
		this.id = id;
		this.mName = mName;
		this.mAccount = mAccount;
		this.imgCard = imgCard;
		this.imgHead = imgHead;
		this.address = address;
		this.status = status;
		this.isBan = isBan;
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmAccount() {
		return mAccount;
	}
	public void setmAccount(String mAccount) {
		this.mAccount = mAccount;
	}
	public String getImgCard() {
		return imgCard;
	}
	public void setImgCard(String imgCard) {
		this.imgCard = imgCard;
	}
	public String getImgHead() {
		return imgHead;
	}
	public void setImgHead(String imgHead) {
		this.imgHead = imgHead;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Boolean getIsBan() {
		return isBan;
	}
	public void setIsBan(Boolean isBan) {
		this.isBan = isBan;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
