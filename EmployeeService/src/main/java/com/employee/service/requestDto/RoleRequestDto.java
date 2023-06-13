package com.employee.service.requestDto;

import java.util.HashSet;
import java.util.Set;

import com.employee.service.entities.RolePermission;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleRequestDto {

	private Integer roleId;
	private String roleName;
	private Set<RolePermissionRequestDto> rolePermission = new HashSet<>();
	
}
