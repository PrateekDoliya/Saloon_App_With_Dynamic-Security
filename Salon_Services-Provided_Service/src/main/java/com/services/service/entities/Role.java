package com.services.service.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {

	@Id
	private Integer roleId;
	private String roleName;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private Set<RolePermission> rolePermissions = new HashSet<>();
	
	
}
