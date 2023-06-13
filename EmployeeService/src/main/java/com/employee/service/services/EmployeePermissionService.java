package com.employee.service.services;

import com.employee.service.requestDto.EmployeePermissionRequestDto;
import com.employee.service.responseDto.EmployeePermissionResponseDto;
import com.employee.service.responseDto.EmployeeResponseDto;

public interface EmployeePermissionService {

	// grant permission to employee
	EmployeeResponseDto grantPermission(Integer employeeId, EmployeePermissionRequestDto employeePermissionRequestDto);
}
