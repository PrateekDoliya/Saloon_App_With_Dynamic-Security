package com.payment.service.services.impl;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.service.entities.MyOrder;
import com.payment.service.exception.CapturePaymentException;
import com.payment.service.repository.MyOrderRepository;
import com.payment.service.requestDto.CreateOrderRequestDto;
import com.payment.service.requestDto.SavePaymentRequestDto;
import com.payment.service.responseDto.MyOrderResponseDto;
import com.payment.service.responseDto.OrderCreatedResponseDto;
import com.payment.service.services.MyPaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class MyPaymentServiceImpl implements MyPaymentService {

	@Autowired
	private MyOrderRepository myOrderRepository;
	
	@Autowired
	private ModelMapper mapper;
	private String SECRET_KEY;
	private String KEY_ID;

	@Value("${razorpay.secretkey}")
	public void setSecret(String secret) {
		this.SECRET_KEY = secret;
	}
	
	@Value("${razorpay.keyid}")
	public void setJwtExpirationInMs(String keyId) {
		this.KEY_ID = keyId;
	}
	
	@Override
	public OrderCreatedResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto) throws RazorpayException {
		Long amount = createOrderRequestDto.getAmount();
		RazorpayClient razorpayClient = new RazorpayClient(this.KEY_ID, this.SECRET_KEY);
		JSONObject options = new JSONObject();
		options.put("amount", amount*100);
		options.put("currency", createOrderRequestDto.getCurrency());
		options.put("receipt", "txn_235464");
		
		// creating new order
		Order order = razorpayClient.orders.create(options);
		
		// save to DB
		MyOrder myOrder = MyOrder.builder()
			.amount(order.get("amount")+"")
			.orderId(order.get("id"))
			.paymentId(null)
			.status(order.get("status"))
			.userEmail(createOrderRequestDto.getUserEmail())
			.receipt(order.get("receipt")).build();
		
		MyOrder save = this.myOrderRepository.save(myOrder);
		
		OrderCreatedResponseDto orderCreatedResponseDto = OrderCreatedResponseDto.builder()
				.amount(Long.parseLong(order.get("amount").toString()))
				.amount_paid(order.get("amount_paid"))
				.notes(order.get("notes"))
				.created_at(order.get("created_at"))
				.amount_due(Long.parseLong(order.get("amount_due").toString()))
				.currency(order.get("currency"))
				.receipt(order.get("receipt"))
				.id(order.get("id"))
				.entity(order.get("entity"))
				.offer_id(order.get("offer_id").toString())
				.status(order.get("status"))
				.attempts(order.get("attempts"))
				.build();

		return orderCreatedResponseDto;
	}

	@Override
	public MyOrderResponseDto updatePayment(SavePaymentRequestDto savePaymentRequestDto) {
		
		if(savePaymentRequestDto.getOrder_id() == null || savePaymentRequestDto.getOrder_id().isEmpty() )
			throw new CapturePaymentException("Order_Id can not be empty!!");
		if(savePaymentRequestDto.getPayment_id() == null || savePaymentRequestDto.getPayment_id().isEmpty() )
			throw new CapturePaymentException("Payment_Id can not be empty!!");
		
		MyOrder myOrder = this.myOrderRepository.findByOrderId(savePaymentRequestDto.getOrder_id());
		myOrder.setPaymentId(savePaymentRequestDto.getPayment_id());
		myOrder.setStatus(savePaymentRequestDto.getStatus());
		MyOrder savedorder = this.myOrderRepository.save(myOrder);
		return this.mapper.map(savedorder, MyOrderResponseDto.class);
	}

}
