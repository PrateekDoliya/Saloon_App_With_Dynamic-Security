package com.services.service.services;

import java.util.List;

import com.services.service.requestDto.ServiceRequestDto;
import com.services.service.responseDto.ServiceResponseDto;

public interface ServicesService {

	// CREATE
	ServiceResponseDto createService(ServiceRequestDto serviceRequestDto);
	
	// UPDATE
	ServiceResponseDto updateService(Integer serviceId, ServiceRequestDto serviceRequestDto);
	
	// DELETE
	ServiceResponseDto deleteService(Integer serviceId);
	
	// GET ALL
	List<ServiceResponseDto> getAllServices();
	
	// GET BY ID
	ServiceResponseDto getServiceById(Integer serviceId);
	
	// GET BY SERVICE NAME
	List<ServiceResponseDto> getServiceByName(String serviceName);
	
	// GET SERVICES BY EMPLOYEE
	List<ServiceResponseDto> getByEmployeeId(Integer employeeId);
	
	
}
