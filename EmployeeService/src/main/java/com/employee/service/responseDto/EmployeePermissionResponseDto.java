package com.employee.service.responseDto;

import java.util.HashSet;
import java.util.Set;

import com.employee.service.requestDto.PermissionRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeePermissionResponseDto {

	private EmployeeResponseDto employeee;
//	private RoleResponseDto role;
//	private Set<PermissionResponseDto> permissionsDtos = new HashSet<>();
	
}
