package com.elibrary.demo.service;

public interface ValidationService {
	int isEmail(String email);
	int isPhone(String phone);
	int isPassword(String password);
}
