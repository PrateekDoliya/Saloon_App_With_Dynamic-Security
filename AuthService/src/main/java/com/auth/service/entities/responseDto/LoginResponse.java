package com.auth.service.entities.responseDto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse<T> {

	private String token;
	private LocalDate createdAt;
	private T user;
}
