package com.payment.service.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyOrderResponseDto {

	private Long myOrderId;
	private String orderId;
	private String amount;
	private String receipt;
	private String status;
	private String paymentId;
	private String userEmail;
}
