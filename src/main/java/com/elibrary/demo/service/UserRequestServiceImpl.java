package com.elibrary.demo.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elibrary.demo.repository.UserRequestRepository;

@Service
public class UserRequestServiceImpl implements UserRequestService{
	
	private final static Logger LOGGER = Logger.getLogger(UserRequestServiceImpl.class.getName());
	private final static int INIT_LENGTH = 7; 
	
	@Autowired
	private UserRequestRepository requestRepo;

	@Override
	public String generateIdRequest() {
		int size = requestRepo.findAll().size() + 1;
		String sizeLength = String.valueOf(size);
		String id="R";
		if(sizeLength.length()<=INIT_LENGTH) {
			int number= INIT_LENGTH-sizeLength.length();
			for(int i=0; i<number; i++) {
				id+="0";
			}
			id+=size;
		}else {
			LOGGER.getLogger("Out of range in UserRequests");
		}
		return id;
	}

}
