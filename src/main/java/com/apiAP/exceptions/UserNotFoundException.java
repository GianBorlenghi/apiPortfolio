package com.apiAP.exceptions;



import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {

	
	public UserNotFoundException(String message, String code, HttpStatus status) {
		super(message, code, status);
		
	}


}
