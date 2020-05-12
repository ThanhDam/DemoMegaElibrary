package com.elibrary.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elibrary.demo.model.UserRequests;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequests, String>{
	@Query("select u from UserRequests u where u.validFlag = 1")
	List<UserRequests> getAllActiveRequest();
}
