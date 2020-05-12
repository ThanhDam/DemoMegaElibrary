package com.elibrary.demo.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.elibrary.demo.repository.UserLogRepository;

public class UserLogServiceImpl implements UserLogService{

	private final static Logger LOGGER = Logger.getLogger(UserLogServiceImpl.class.getName());
	private final static int INIT_LENGTH = 9; 
	
	@Autowired
	private UserLogRepository logRepo;
	
	@Override
	public String generateIdLog() {
		int size = logRepo.findAll().size() + 1;
		String sizeLength = String.valueOf(size);
		String id="L";
		if(sizeLength.length() <= INIT_LENGTH) {
			int number = INIT_LENGTH-sizeLength.length();
			for(int i=0; i<number; i++) {
				id+="0";
			}
			id+=size;
		}else {
			LOGGER.getLogger("Out of range in UserLogs");
		}
		return id;
	}

}
