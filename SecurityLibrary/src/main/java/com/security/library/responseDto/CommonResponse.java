package com.security.library.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponse {

	private String message;
	private Boolean success;
	private Integer statusCode;
	private Object data;
	
}
