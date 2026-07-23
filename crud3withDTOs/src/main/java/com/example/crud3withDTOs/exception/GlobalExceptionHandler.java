package com.example.crud3withDTOs.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	//returns error in string eg: Student not found
//	@ExceptionHandler(RuntimeException.class)
//	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
//		// TODO Auto-generated method stub
//		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
		// TODO Auto-generated method stub
		Map<String, String> error = new HashMap<String, String>();
		error.put("message", ex.getMessage());
		return new ResponseEntity<Map<String,String>>(error,HttpStatus.NOT_FOUND);
	}
	
}
