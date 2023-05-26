package com.employee.service.requestDto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleRequestDto {

	private String roleName;
	private Set<String> authorities = new HashSet<>();
	
	
}
