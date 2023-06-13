package com.employee.service.requestDto;

import java.util.HashSet;
import java.util.Set;

import com.employee.service.entities.Permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeePermissionRequestDto {

	private Integer roleId;
	private Set<Integer> permissions = new HashSet<>();
//	private Set<PermissionRequestDto> permissionsDtos = new HashSet<>();
	
}
