package com.Ecommerce.user.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	ResourceNotFoundException(String msg){
		super(msg);
	}

}
