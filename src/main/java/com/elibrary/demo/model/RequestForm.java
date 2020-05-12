package com.elibrary.demo.model;

public class RequestForm {
	private String email;
	private String phone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RequestForm(String email, String phone) {
		super();
		this.email = email;
		this.phone = phone;
	}

	public RequestForm() {
		super();
	}

	
	
	
}
