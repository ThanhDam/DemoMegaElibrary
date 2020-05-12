package com.elibrary.demo.service;

public interface AccountService {
	String encodePassword(String psw);
	boolean checkPassword(String password, String pswDatabase);
	String generateID();
	boolean checkAdminByUser(String idUser);
	boolean checkAdminBySession(String idSession);
}
