package com.bitguiders.bjsbt_01.dataaccess.orm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserORM {
	public Long userId;
	public String userName;
	public String phone;
	
	@Id
	@GeneratedValue
	public Long getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
