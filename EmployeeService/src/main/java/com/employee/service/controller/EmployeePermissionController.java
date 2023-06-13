package com.employee.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.service.requestDto.EmployeePermissionRequestDto;
import com.employee.service.responseDto.EmployeePermissionResponseDto;
import com.employee.service.responseDto.EmployeeResponseDto;
import com.employee.service.services.EmployeePermissionService;

@RestController
@RequestMapping("/api/v1/permission")
public class EmployeePermissionController {

	@Autowired
	private EmployeePermissionService employeePermissionService;
	
	@PostMapping("/grant/employee/{employeeId}")
	public ResponseEntity<EmployeeResponseDto> grantPermission( @PathVariable Integer employeeId, @RequestBody EmployeePermissionRequestDto employeePermissionRequestDto) {
		EmployeeResponseDto employeeResponseDto = this.employeePermissionService.grantPermission(employeeId, employeePermissionRequestDto);
		return ResponseEntity.status(HttpStatus.OK)
				.body(employeeResponseDto);
	}
}
