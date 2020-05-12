package com.elibrary.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elibrary.demo.model.Ebooks;

@Repository
public interface EbookRepository extends JpaRepository<Ebooks, String>{
	@Query("select e from Ebooks e where e.title like %?1")
	List<Ebooks> findByTitle(String title);
	
	@Query("select e from Ebooks e where e.author like %?1")
	List<Ebooks> findByAuthor(String author);
	
	@Query("select e from Ebooks e join Categories c on e.idCategory = c.idCategory "
			+ "where c.description like %?1")
	List<Ebooks> findByCategory(String category);
}
