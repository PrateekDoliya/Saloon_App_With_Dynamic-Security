package com.services.service.entities;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

	private Integer roleId;
	private String roleName;
	private Set<String> authorities = new HashSet<>();
	
	
}
