package com.services.service.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

	private Integer customerId;
	private String customerName;
	private String mobileNumber;
	private String customerEmail;
	private LocalDate appointmentDate;
	private LocalDate dateOfOrder;
	private Employee employee;
	
}
