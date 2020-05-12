package com.elibrary.demo.model;

public class UserForm {
	
	private String email;
	private String password;
	private String rePassword;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public UserForm(String email, String password, String rePassword) {
		this.email = email;
		this.password = password;
		this.rePassword = rePassword;
	}
	public UserForm() {
		
	}
	
	
}
