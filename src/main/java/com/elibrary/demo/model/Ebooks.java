package com.elibrary.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ebooks")
public class Ebooks {
	private String idEbook;
	private String title;
	private String author;
	private int size;
	private String directory;
	private String image;
	private int idCategory;
	
	@Id
	public String getIdEbook() {
		return idEbook;
	}
	public void setIdEbook(String idEbook) {
		this.idEbook = idEbook;
	}
	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "author", nullable = true)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name = "size", nullable = false)
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Column(name = "directory", nullable = false)
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	@Column(name = "image", nullable = true)
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Column(name = "idCategory", nullable = false)
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public Ebooks() {
		
	}
	public Ebooks(String idEbook, String title, String author, int size, String directory, String image,
			int idCategory) {
		this.idEbook = idEbook;
		this.title = title;
		this.author = author;
		this.size = size;
		this.directory = directory;
		this.image = image;
		this.idCategory = idCategory;
	}
	
	
	

}
