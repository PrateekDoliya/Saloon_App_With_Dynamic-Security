package com.employee.service.requestDto;

import java.util.HashSet;
import java.util.Set;

import com.employee.service.entities.RolePermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequestDto {

	private Integer permissionId;
	private String permission;
	private Set<RolePermissionRequestDto> rolePermissions = new HashSet<>();
	
}
