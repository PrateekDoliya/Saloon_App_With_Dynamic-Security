package com.sloon.customer.payloads;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

	@Id
	private Integer aid;
	
	private String localStreet;
	
	private String city;
	
	private String state;
}
