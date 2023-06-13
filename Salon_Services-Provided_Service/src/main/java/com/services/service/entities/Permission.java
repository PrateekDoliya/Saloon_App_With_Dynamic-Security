package com.services.service.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer permissionId;
	private String permission;
	@JsonIgnore
	@OneToMany(mappedBy = "permission")
	private Set<RolePermission> rolePermissions = new HashSet<>();
}
