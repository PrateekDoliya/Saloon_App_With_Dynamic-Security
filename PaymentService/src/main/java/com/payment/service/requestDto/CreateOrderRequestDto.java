package com.payment.service.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderRequestDto {

	private Long amount;
	private String currency;
	private String userEmail;
	
}
