package com.sloon.customer.payloads;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private Integer id;
	
	private String customerName;
	
	private String mobileNumber;
	
	private String email;
	
	private LocalDate dateOfAppointment;
	
	private LocalDate dateOfBooking;
	
	private Integer employee;
	
	private EmployeeDTO employeeDTO;

}
