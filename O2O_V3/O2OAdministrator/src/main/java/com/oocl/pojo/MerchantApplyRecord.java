package com.oocl.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
@GenericGenerator(strategy="uuid", name="o2o_applyRecord")
public class MerchantApplyRecord implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="o2o_applyRecord")
	private String id ;
	
	/**
	 * merchant id
	 */
	@Column(length=36)
	private String mid;
	
	/**
	 * merchant account name
	 */
	@Column(length=24,unique=true,nullable=true)
	private String name;
	
	/**
	 * merchant shop name
	 */
	@Column(length=32,nullable=true)
	private String shopName;
	
	/**
	 * idcard's img path
	 */
	@Column(length=128)
	private String idcard;
	
	/**
	 * heading's img path
	 */
	@Column(length=128)
	private String heading;
	
	/**
	 * merchant address
	 */
	@Column(length=128)
	private String address;
	
	/**
	 * approval status
	 */
	private int status;
	
	/**
	 * ban status
	 */
	@Column(columnDefinition="char(1)")
	private boolean ban;
	
	@Column(length=128)
	private String bContent;
	
	public MerchantApplyRecord() {
		super();
	}

	public MerchantApplyRecord(String id, String mid, String name,
			String shopName, String idcard, String heading, String address,
			int status, boolean ban, String bContent) {
		super();
		this.id = id;
		this.mid = mid;
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

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MerchantApplyRecord other = (MerchantApplyRecord) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MerchantApplyRecord [id=" + id + ", mid=" + mid + ", name="
				+ name + ", shopName=" + shopName + ", idcard=" + idcard
				+ ", heading=" + heading + ", address=" + address + ", status="
				+ status + ", ban=" + ban + ", bContent=" + bContent + "]";
	}
	
}
