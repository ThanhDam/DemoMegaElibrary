package com.elibrary.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elibrary.demo.model.Accounts;
import com.elibrary.demo.model.Roles;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, String>{
	@Query("select a from Accounts a where a.email like %?1")
	Accounts findByEmail(String email);
	
	@Query("select r from Roles r join Accounts a on r.idRole = a.idRole where a.idUser = ?1")
	Roles getRoleByUser(String idUser);
	
	@Query("select r from Roles r join Accounts a on r.idRole = a.idRole join UserLogs u on a.idUser = u.idUser "
			+ "where u.sessionId = ?1")
	Roles getRolesBySession(String sessionId);
}
