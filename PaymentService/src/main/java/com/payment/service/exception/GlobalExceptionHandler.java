package com.payment.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.payment.service.responseDto.CommonResponseDto;
import com.razorpay.RazorpayException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RazorpayException.class)
	public ResponseEntity<?> handelRazorpayException(RazorpayException exception) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new CommonResponseDto(exception.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
	}
	
	@ExceptionHandler(CapturePaymentException.class)
	public ResponseEntity<?> handelCapturePaymentException(CapturePaymentException exception) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new CommonResponseDto(exception.getMessage(), false, HttpStatus.BAD_REQUEST.value(), null));
	}
	
}
