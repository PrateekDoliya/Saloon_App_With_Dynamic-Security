package com.auth.service.services;

import com.auth.service.requestDto.LoginRequest;

public interface AuthenticationService<T> {

	// LOGIN
	T doLogin(LoginRequest loginRequest);
		
}
