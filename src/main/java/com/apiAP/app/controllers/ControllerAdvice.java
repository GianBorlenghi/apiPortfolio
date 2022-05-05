package com.apiAP.app.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.apiAP.app.dto.ErrorDTO;
import com.apiAP.exceptions.BusinessException;
import com.apiAP.exceptions.RequestException;
import com.apiAP.exceptions.UserNotFoundException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex){
		
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setCode("P-500");
		errorDTO.setMessage(ex.getMessage());
		errorDTO.setTimeStamp(new Date());
		return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(RequestException.class)
	public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex){
		
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(ex.getMessage());
		errorDTO.setCode(ex.getCode());
		errorDTO.setTimeStamp(new Date());
		return new ResponseEntity<>(errorDTO,ex.getStatus());
	} 
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDTO> businessExceptionHandler(BusinessException ex){
		
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setCode(ex.getCode());
		errorDTO.setMessage(ex.getMessage());
		errorDTO.setTimeStamp(new Date());
		return new ResponseEntity<>(errorDTO,ex.getStatus());
	} 
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDTO> userNotFoundExceptionHandler(UserNotFoundException ex){
		
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setCode(ex.getCode());
		errorDTO.setMessage(ex.getMessage());
		errorDTO.setTimeStamp(new Date());
		return new ResponseEntity<>(errorDTO,ex.getStatus());
	} 
}
