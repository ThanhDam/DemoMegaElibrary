package com.elibrary.demo.service;

import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService{

	/**
	 * If param is null, return 1
	 * If param is invalid, return 2
	 * If param is valid, return 0
	 */
	@Override
	public int isEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
		Pattern pattern = Pattern.compile(emailRegex);
		if(email == null) {
			return 1;
		}
		if(pattern.matcher(email).matches()) {
			return 0;
		}
		return 2;
	}

	@Override
	public int isPhone(String phone) {
		String phoneRegex = "^\\+(?:[0-9] ?){10}[0-9]$";
		Pattern pattern = Pattern.compile(phoneRegex);
		if(phone == null) {
			return 1;
		}
		if(pattern.matcher(phone).matches()) {
			return 0;
		}
		return 2;
	}

	@Override
	public int isPassword(String password) {
		String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,50}$";
		Pattern pattern = Pattern.compile(passwordRegex);
		if(password == null) {
			return 1;
		}
		if(pattern.matcher(password).matches()) {
			return 0;
		}
		return 2;
	}

}
