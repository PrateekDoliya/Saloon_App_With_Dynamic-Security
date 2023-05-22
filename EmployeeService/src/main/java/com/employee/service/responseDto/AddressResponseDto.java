package com.employee.service.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressResponseDto {

	private Integer addressId;
	private String street;
	private String city;
	private String State;
	private String country;
}
