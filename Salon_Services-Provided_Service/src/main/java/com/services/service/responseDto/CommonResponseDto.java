package com.services.service.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommonResponseDto {

	private String message;
	private Boolean success;
	private Integer statusCode;
	private Object data;
	
}
