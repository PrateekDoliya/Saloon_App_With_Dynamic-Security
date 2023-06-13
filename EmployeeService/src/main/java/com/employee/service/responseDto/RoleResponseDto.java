package com.employee.service.responseDto;

import java.util.HashSet;
import java.util.Set;

import com.employee.service.entities.RolePermission;
import com.employee.service.requestDto.RolePermissionRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleResponseDto {

	private Integer roleId;
	private String roleName;
	private Set<RolePermissionResponseDto> rolePermissions = new HashSet<>();
	
	
}
