package com.services.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.service.requestDto.ServiceRequestDto;
import com.services.service.responseDto.ApiResponse;
import com.services.service.responseDto.ServiceResponseDto;
import com.services.service.services.ServicesService;

@RestController
@RequestMapping("/api/v1/services")
public class ServicesController {

	@Autowired
	private ServicesService servicesService;

	// CREATE
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') and hasAuthority('WRITE')")
	public ResponseEntity<ApiResponse<ServiceResponseDto>> createServices(
			@RequestBody ServiceRequestDto serviceRequestDto) {
		ServiceResponseDto createService = this.servicesService.createService(serviceRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<ServiceResponseDto>("Service Created Successfully", true, createService));
	}

	// UPDATE
	@PreAuthorize("hasAuthority('UPDATE')")
	@PutMapping("/update/{serviceId}")
	public ResponseEntity<ApiResponse<ServiceResponseDto>> updateService(
			@RequestBody ServiceRequestDto serviceRequestDto, @PathVariable Integer serviceId) {
		ServiceResponseDto updateService = this.servicesService.updateService(serviceId, serviceRequestDto);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<ServiceResponseDto>("Service Updated Successfully", true, updateService));
	}

	// DELETE
	@PreAuthorize("hasAuthority('DELETE')")
	@DeleteMapping("/delete/{serviceId}")
	public ResponseEntity<ApiResponse<ServiceResponseDto>> deleteService(@PathVariable Integer serviceId) {
		ServiceResponseDto deleteService = this.servicesService.deleteService(serviceId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<ServiceResponseDto>("Service deleted Successfully", true, deleteService));
	}

	// GET ALL
	@GetMapping("/get-all")
	@PreAuthorize("hasAuthority('READ')")
	public ResponseEntity<ApiResponse<List<ServiceResponseDto>>> getAllServices() {
		List<ServiceResponseDto> allServices = this.servicesService.getAllServices();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<List<ServiceResponseDto>>("Fetched Successfully", true, allServices));
	}

	// GET BY ID
	@GetMapping("/get/{serviceId}")
	@PreAuthorize("hasAuthority('READ')")
	public ResponseEntity<ApiResponse<ServiceResponseDto>> getServiceById(@PathVariable Integer serviceId) {
		ServiceResponseDto serviceById = this.servicesService.getServiceById(serviceId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<ServiceResponseDto>("Fetched Successfully", true, serviceById));
	}

	// GET BY NAME
	@GetMapping("/get-all/by-name/{serviceName}")
	@PreAuthorize("hasAuthority('READ')")
	public ResponseEntity<ApiResponse<List<ServiceResponseDto>>> getAllServicesByName(
			@PathVariable String serviceName) {
		List<ServiceResponseDto> serviceByName = this.servicesService.getServiceByName(serviceName);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<List<ServiceResponseDto>>("Fetched Successfully", true, serviceByName));
	}

	// GET BY EMPLOYEE ID
	@GetMapping("/get-all/by-employee/{employeeId}")
	@PreAuthorize("hasAuthority('READ')")
	public ResponseEntity<ApiResponse<List<ServiceResponseDto>>> getAllServicesByEmployeeId(
			@PathVariable Integer employeeId) {
		List<ServiceResponseDto> serviceByEmployee = this.servicesService.getByEmployeeId(employeeId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<List<ServiceResponseDto>>("Fetched Successfully", true, serviceByEmployee));
	}

}