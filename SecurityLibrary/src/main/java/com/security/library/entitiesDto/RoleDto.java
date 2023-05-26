package com.security.library.entitiesDto;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "role")
public class RoleDto {

	@Id
	private Integer roleId;
	private String roleName;
	private Set<String> authorities = new HashSet<>();
}
