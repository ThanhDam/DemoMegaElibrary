package com.elibrary.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elibrary.demo.model.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer>{
	//Can use getCateByShort(String short) same as:
	// Categories cate = new Categories();
	// cate.setShortCode("AA");
	// cateRepo.findOne(Example.of(cate));
}
