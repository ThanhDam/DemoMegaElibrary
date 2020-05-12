package com.elibrary.demo.model;

import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "uploadBooks")
public class UploadBooks {
	
	private String idUpload;
	private String idUser;
	private String idEbook;
	private Date uploadDate;
	@Id
	public String getIdUpload() {
		return idUpload;
	}
	public void setIdUpload(String idUpload) {
		this.idUpload = idUpload;
	}
	@Column(name = "idUser", nullable = false)
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	@Column(name = "idEbook", nullable = false)
	public String getIdEbook() {
		return idEbook;
	}
	public void setIdEbook(String idEbook) {
		this.idEbook = idEbook;
	}
	@Column(name = "uploadDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public UploadBooks() {
		
	}
	public UploadBooks(String idUpload, String idUser, String idEbook, Date uploadDate) {
		this.idUpload = idUpload;
		this.idUser = idUser;
		this.idEbook = idEbook;
		this.uploadDate = uploadDate;
	}
	
	

}
