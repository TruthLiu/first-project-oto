package com.oocl.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
//@GenericGenerator(strategy="uuid", name="o2o_merchant")
public class Merchant {
	@Id
//	@GeneratedValue(generator="o2o_merchant")
	private String id ;
	
	@Column(length=24,unique=true,nullable=true)
	private String name;
	
	@Column(length=32,nullable=true)
	private String shopName;
	
	@Column(length=128)
	private String idcard;
	
	@Column(length=128)
	private String heading;
	
	@Column(length=128)
	private String address;
	
	private int status;
	
	@Column(columnDefinition="char(1)")
	private boolean ban;
	
	@Column(length=128)
	private String bContent;
	
	public Merchant() {
		super();
	}


	public Merchant(String id, String name, String shopName, String idcard,
			String heading, String address, int status, boolean ban,
			String bContent) {
		super();
		this.id = id;
		this.name = name;
		this.shopName = shopName;
		this.idcard = idcard;
		this.heading = heading;
		this.address = address;
		this.status = status;
		this.ban = ban;
		this.bContent = bContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isBan() {
		return ban;
	}

	public void setBan(boolean ban) {
		this.ban = ban;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


	@Override
	public String toString() {
		return "Merchant [id=" + id + ", name=" + name + ", shopName="
				+ shopName + ", idcard=" + idcard + ", heading=" + heading
				+ ", address=" + address + ", status=" + status + ", ban="
				+ ban + ", bContent=" + bContent + "]";
	}

}
