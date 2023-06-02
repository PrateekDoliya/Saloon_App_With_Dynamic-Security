package com.services.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.services.service.responseDto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHander<T> {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<T>> handelResourceNotFoundException(ResourceNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<T>(exception.getMessage(), false, null));
	}
	
}
