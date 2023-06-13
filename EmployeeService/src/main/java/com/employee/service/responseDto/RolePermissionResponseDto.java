package com.employee.service.responseDto;

import com.employee.service.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolePermissionResponseDto {

	@JsonIgnore
	private Integer id;
//	@JsonIgnore
//	private RoleResponseDto role;
//	private Integer employeeId;
	private PermissionResponseDto permission;
	
	
}
