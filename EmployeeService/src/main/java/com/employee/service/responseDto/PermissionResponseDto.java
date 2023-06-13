package com.employee.service.responseDto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionResponseDto {

	private Integer permissionId;
	private String permission;
//	@JsonIgnore
//	private Set<RolePermissionResponseDto> rolePermissions = new HashSet<>();
	
}
