package com.yan.goods.delivery.domain;

import java.io.Serializable;
import java.util.Date;

public class Address implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Address() {
		super();
	}

	public Address(Integer userId, String userName, String address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.address = address;
	}

	private Integer id;

    private Integer userId;

    private String userName;

    private Byte status;

    private String address;

    private Byte deleted;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "Address [userId=" + userId + ", userName=" + userName
				+ ", address=" + address + "]";
	}
    
}