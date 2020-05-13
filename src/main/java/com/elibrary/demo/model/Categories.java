package com.elibrary.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Categories {
	private int idCategory;
	private String description;
	private String shortCode;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "shortCode", nullable = false)
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	
	public Categories(int idCategory, String description, String shortCode) {
		this.idCategory = idCategory;
		this.description = description;
		this.shortCode = shortCode;
	}
	public Categories() {
		
	}
	
	
}
