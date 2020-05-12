package com.elibrary.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userRequests")
public class UserRequests {
	private String idRequest;
	private String email;
	private String phone;
	private int validFlag;
	
	@Id
	public String getIdRequest() {
		return idRequest;
	}
	public void setIdRequest(String idRequest) {
		this.idRequest = idRequest;
	}
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "phoneNumber", nullable = true)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "validFlag", nullable = false)
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}
	public UserRequests(String idRequest, String email, String phone, int validFlag) {
		this.idRequest = idRequest;
		this.email = email;
		this.phone = phone;
		this.validFlag = validFlag;
	}
	public UserRequests() {
		super();
	}
	
	
	
	
	
}
