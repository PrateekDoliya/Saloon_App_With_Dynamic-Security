package com.employee.service.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.service.responseDto.CommonResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CommonResponseDto> handelResourceNotFoundException(ResourceNotFoundException exception)
	{
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(CommonResponseDto.builder().message(exception.getMessage()).statusCode(HttpStatus.NOT_FOUND.value()).success(false).build());
	}
	
	@ExceptionHandler(org.hibernate.PropertyValueException.class)
	public ResponseEntity<CommonResponseDto> handelPropertyValueException(PropertyValueException exception) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(CommonResponseDto.builder().message(exception.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).success(false).build());
	}
	
	@ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<CommonResponseDto> handelSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(CommonResponseDto.builder().message(exception.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).success(false).build());
	}
	
}
