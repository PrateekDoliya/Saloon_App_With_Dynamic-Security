package com.payment.service.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SavePaymentRequestDto {

	private String payment_id;
	private String order_id;
	private String status;
	
}
