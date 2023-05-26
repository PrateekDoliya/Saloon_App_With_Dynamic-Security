package com.payment.service.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponseDto {

	private String message;
	private Boolean success;
	private Integer statusCode;
	private Object data;
	
}
