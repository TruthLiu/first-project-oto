package com.oocl.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@GenericGenerator(strategy="uuid", name="o2o_ad")
public class Advertisement implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="o2o_ad")
	private String id;
	
	/**
	 * merchant id
	 */
	@Column(length=36)
	private String mid;
	
	/**
	 * dish id
	 */
	@Column(length=36)
	private String did;
	
	/**
	 * dish image path
	 */
	@Column(length=128)
	private String img;
	
	/**
	 * the time store in DB
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/**
	 * tender price
	 */
	@Column(columnDefinition="number")
	private double price;
	
	/**
	 * the approval status:
	 * 0: had not approval
	 * 1: approvaled
	 * 2: history dealed
	 */
	private int status;
	
	/**
	 * 0: not be recommended
	 * 1: be recommended
	 */
	private int recommend;
	
	@ManyToOne(targetEntity=Merchant.class,fetch=FetchType.EAGER)
	@JoinColumn(name="m_id")
	private Merchant merchant;
	
	public Advertisement(){
		super();
	}

	public Advertisement(String id, String mid, String did, String img,
			Date createTime, double price, int status, int recommend) {
		super();
		this.id = id;
		this.mid = mid;
		this.did = did;
		this.img = img;
		this.createTime = createTime;
		this.price = price;
		this.status = status;
		this.recommend = recommend;
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

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", mid=" + mid + ", did=" + did
				+ ", img=" + img + ", createTime=" + createTime + ", price="
				+ price + ", status=" + status + ", recommend=" + recommend
				+ ", merchant=" + merchant + "]";
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
		Advertisement other = (Advertisement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
