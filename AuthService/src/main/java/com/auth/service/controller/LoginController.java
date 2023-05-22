package com.auth.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.requestDto.LoginRequest;
import com.auth.service.services.AuthenticationService;

@RestController
@RequestMapping("/api/v1/authenticate")
public class LoginController<T> {

	@Autowired
	private AuthenticationService authenticationService;
	
	// LOGIN
	@PostMapping("/signin")
	public T doLogin( @RequestBody LoginRequest request ) {
		
		Object login = this.authenticationService.doLogin(request);
		return (T) login;
	}
}
