package com.employee.service.services;

import java.util.List;

import com.employee.service.requestDto.EmployeeRequestDto;
import com.employee.service.responseDto.EmployeeResponseDto;

public interface EmployeeService {

	// CREATE
	EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
	
	// GET ALL
	List<EmployeeResponseDto> getAllEmployees();
	
	// GET BY ID
	EmployeeResponseDto getEmployeeById(Integer employeeId);
	
	// GET BY EMAIL
	EmployeeResponseDto getEmployeeByEmail(String email);
	
}
