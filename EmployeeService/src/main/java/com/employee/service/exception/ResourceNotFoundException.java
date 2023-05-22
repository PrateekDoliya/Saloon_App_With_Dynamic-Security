package com.employee.service.exception;

public class ResourceNotFoundException extends RuntimeException {

	
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldvalue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldvalue));
	}
	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldvalue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldvalue));
	}
	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
