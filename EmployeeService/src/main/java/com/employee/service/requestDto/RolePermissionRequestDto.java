package com.employee.service.requestDto;

import com.employee.service.entities.Permission;
import com.employee.service.entities.Role;
import com.employee.service.responseDto.RoleResponseDto;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolePermissionRequestDto {

	private Integer id;
	private RoleResponseDto role;
	private PermissionRequestDto permission;
	private Integer employeeId;
}
