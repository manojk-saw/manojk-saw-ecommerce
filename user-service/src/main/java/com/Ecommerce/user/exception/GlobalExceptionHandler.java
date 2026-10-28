package com.Ecommerce.user.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<Map<String ,Object>> resourceAlreadyExistException(ResourceAlreadyExistException ex){
		Map<String,Object>m=new HashMap<>();
		m.put("error","Conflict");
		m.put("message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(m);
		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,Object>>resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		
		Map<String ,Object>m=new HashMap<>();
		m.put("error","Not Found");
		m.put("message",ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>>handlerValidation(MethodArgumentNotValidException ex){
		
		Map<String ,Object>error=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(err->error.put(err.getField(),err.getDefaultMessage()));
		Map<String, Object>body=Map.of("error","Validation Failed","detials",error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>>handleException(Exception ex){
		
		Map<String,Object>m=Map.of("error","Internal Server error","message",ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(m);
		
	}
}
