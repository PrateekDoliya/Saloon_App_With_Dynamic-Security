package com.employee.service.requestDto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequestDto {

	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String mobileNumber;
	private LocalDate dateOfBirth;
	private Integer roleId;
	private AddressRequestDto address;
	
}
