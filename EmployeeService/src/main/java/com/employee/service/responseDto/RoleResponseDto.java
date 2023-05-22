package com.employee.service.responseDto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleResponseDto {

	private Integer roleId;
	private String roleName;
	private Set<String> authorities = new HashSet<>();
	
	
}
