package com.services.service.requestDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.services.service.entities.Customer;
import com.services.service.entities.Employee;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceRequestDto {

	private String service;
	private List<Integer> employeeIds= new ArrayList<>();
	private List<Customer> customers = new ArrayList<>();
//	private EmployeeResponseDto employee;
//	private Date appointmentBooking;
	
}
