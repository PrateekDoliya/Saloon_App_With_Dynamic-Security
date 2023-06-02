package com.services.service.external.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.services.service.responseDto.CommonResponseDto;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface EmployeeServiceClient {

	@GetMapping("/get/id/{employeeId}")
	@PreAuthorize("hasAuthority('READ')")
	public ResponseEntity<CommonResponseDto> getEmployeeById(@RequestHeader(value = "Authorization", required = true) String requestToken,@PathVariable Integer employeeId);
	
}
