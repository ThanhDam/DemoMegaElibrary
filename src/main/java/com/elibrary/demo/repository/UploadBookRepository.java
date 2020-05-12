package com.elibrary.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elibrary.demo.model.Ebooks;
import com.elibrary.demo.model.UploadBooks;

@Repository
public interface UploadBookRepository extends JpaRepository<UploadBooks, String>{
	@Query("select u from UploadBooks u where u.idUser = ?1")
	List<UploadBooks> findByUser(String idUser);
	
	@Query("select e from Ebooks e join UploadBooks u on e.idEbook = u.idEbook"
			+ " where u.idUser = ?1 ")
	List<Ebooks> getListBookByUser(String idUser);
}
