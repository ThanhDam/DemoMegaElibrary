package com.elibrary.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Accounts {
	
	private String idUser;
	private String email;
	private String password;
	private int idRole;
	private int validFlag;
	
	@Id
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "idRole", nullable = false)
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	@Column(name = "validFlag", nullable = false)
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}
	public Accounts(String idUser, String email, String password, int idRole, int validFlag) {
		this.idUser = idUser;
		this.email = email;
		this.password = password;
		this.idRole = idRole;
		this.validFlag = validFlag;
	}
	public Accounts() {
		
	}
	
	

}
