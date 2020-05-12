package com.elibrary.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elibrary.demo.model.UserLogs;

@Repository
public interface UserLogRepository extends JpaRepository<UserLogs, String>{
	@Query("select u from UserLogs u where u.idUser = ?1")
	List<UserLogs> findByUser(String idUser);
	
	@Query("select u from UserLogs u where u.sessionId = ?1")
	UserLogs findBySessionId(String sessionId);
}
