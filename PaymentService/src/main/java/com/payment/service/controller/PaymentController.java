package com.payment.service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.service.requestDto.CreateOrderRequestDto;
import com.payment.service.requestDto.SavePaymentRequestDto;
import com.payment.service.responseDto.MyOrderResponseDto;
import com.payment.service.responseDto.OrderCreatedResponseDto;
import com.payment.service.services.MyPaymentService;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

	@Autowired
	private MyPaymentService paymentService;
	
	@PostMapping("/create-order")
	public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) throws RazorpayException {
		OrderCreatedResponseDto createOrder = this.paymentService.createOrder(createOrderRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createOrder);
	}

	@PostMapping("/update-order")
	public ResponseEntity<?> updatePayment(@RequestBody SavePaymentRequestDto savePaymentRequestDto) {
		MyOrderResponseDto updatePayment = this.paymentService.updatePayment(savePaymentRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(updatePayment);
	}

}
