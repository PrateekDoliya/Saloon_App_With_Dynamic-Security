package com.security.library.entitiesDto;

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
@Table(name = "address")
public class AddressDto {

	@Id
	private Integer addressId;
	private String street;
	private String city;
	private String State;
	private String country;
}
