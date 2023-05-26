package com.payment.service.services;

import com.payment.service.requestDto.CreateOrderRequestDto;
import com.payment.service.requestDto.SavePaymentRequestDto;
import com.payment.service.responseDto.MyOrderResponseDto;
import com.payment.service.responseDto.OrderCreatedResponseDto;
import com.razorpay.RazorpayException;

public interface MyPaymentService {

	OrderCreatedResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto) throws RazorpayException;
	MyOrderResponseDto updatePayment(SavePaymentRequestDto savePaymentRequestDto);
	
}
