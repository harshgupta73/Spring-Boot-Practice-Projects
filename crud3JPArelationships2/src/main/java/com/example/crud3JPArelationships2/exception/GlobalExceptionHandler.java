package com.example.crud3JPArelationships2.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
		// TODO Auto-generated method stub
		Map<String, String> error = new HashMap<String, String>();
		error.put("message", ex.getMessage());
		
		return new ResponseEntity<Map<String,String>>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<Map<String, String>> handleDuplicateResource(DuplicateResourceException ex) {
		// TODO Auto-generated method stub
		Map<String, String> error = new HashMap<String, String>();
		error.put("message", ex.getMessage());
		
		return new ResponseEntity<Map<String,String>>(error,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> validationException(MethodArgumentNotValidException ex) {
		// TODO Auto-generated method stub
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(), error.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
		// TODO Auto-generated method stub
		Map<String, String> error = new HashMap<String, String>();
		error.put("message", ex.getMessage());
		
		return new ResponseEntity<Map<String,String>>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DepartmentInUseException.class)
	public ResponseEntity<Map<String, String>> handleDepartmentInUse(DepartmentInUseException ex) {
		// TODO Auto-generated method stub
		Map<String, String> error = new HashMap<String, String>();
		error.put("message", ex.getMessage());
		
		return new ResponseEntity<Map<String,String>>(error,HttpStatus.CONFLICT);
	}
	
}
