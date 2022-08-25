package com.apiAP.app.controllers;

import java.lang.module.FindException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.apiAP.app.dto.ErrorDTO;
import com.apiAP.exceptions.BusinessException;
import com.apiAP.exceptions.RequestException;
import com.apiAP.exceptions.UserNotFoundException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex) {

		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setCode("P-500");
		errorDTO.setMessage(ex.getMessage());
		errorDTO.setTimeStamp(new Date());
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RequestException.class)
	public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex) {

		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(ex.getMessage());
		errorDTO.setCode(ex.getCode());
		errorDTO.setTimeStamp(new Date());
		return new ResponseEntity<>(errorDTO, ex.getStatus());
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDTO> businessExceptionHandler(BusinessException ex) {

		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setCode(ex.getCode());
		errorDTO.setMessage(ex.getMessage());
		errorDTO.setTimeStamp(new Date());
		return new ResponseEntity<>(errorDTO, ex.getStatus());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDTO> userNotFoundExceptionHandler(UserNotFoundException ex) {

		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setCode(ex.getCode());
		errorDTO.setMessage(ex.getMessage());
		errorDTO.setTimeStamp(new Date());
		return new ResponseEntity<>(errorDTO, ex.getStatus());
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();

			errors.put(fieldName, message);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
