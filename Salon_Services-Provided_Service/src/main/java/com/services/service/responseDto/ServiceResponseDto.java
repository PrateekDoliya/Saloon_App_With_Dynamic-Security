package com.services.service.responseDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.services.service.entities.Customer;
import com.services.service.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceResponseDto {

	private Integer serviceId;
	private String service;
	@JsonIgnore
	private List<Integer> employeeIds= new ArrayList<>();
	private List<Customer> customers = new ArrayList<>();
	private List<Employee> employees = new ArrayList<>();
//	private Date appointmentBooking;
	
}
