package com.elibrary.demo.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elibrary.demo.model.Accounts;
import com.elibrary.demo.model.Roles;
import com.elibrary.demo.repository.AccountRepository;



@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepository accountRepo;
	
	private final static Logger DEBUG_LOG = Logger.getLogger(AccountServiceImpl.class.getName());
	private final static int INIT_LENGTH = 9; 
	
	@Override
	public String encodePassword(String psw) {
		return BCrypt.hashpw(psw, BCrypt.gensalt(10));
	}

	@Override
	public boolean checkPassword(String password, String pswDatabase) {
		return BCrypt.checkpw(password, pswDatabase);
	}

	@Override
	public String generateID() {
		int size = accountRepo.findAll().size() + 1;
		String sizeLength = String.valueOf(size);
		String id="U";
		if(sizeLength.length() <= INIT_LENGTH) {
			int number = INIT_LENGTH-sizeLength.length();
			for(int i=0; i<number; i++) {
				id+="0";
			}
			id+=size;
		}else {
			DEBUG_LOG.setLevel(Level.INFO);
			DEBUG_LOG.getLogger("Out of range in Accounts");
		}
		return id;
	}

	@Override
	public boolean checkAdminByUser(String idUser) {
		Roles role = accountRepo.getRoleByUser(idUser);
		if(role.getDescription().trim().equalsIgnoreCase("admin")) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean checkAdminBySession(String idSession) {
		Roles role = accountRepo.getRolesBySession(idSession);
		if(role.getDescription().trim().equalsIgnoreCase("admin")) {
			return true;
		}
		return false;
	}

}
