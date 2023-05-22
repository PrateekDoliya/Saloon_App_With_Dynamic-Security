package com.auth.service.services.impl;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.service.entities.Employee;
import com.auth.service.entities.responseDto.LoginResponse;
import com.auth.service.repository.EmployeeRepository;
import com.auth.service.requestDto.LoginRequest;
import com.auth.service.services.AuthenticationService;
import com.security.library.jwt.util.JwtTokenUtil;
import com.security.library.responseDto.CommonResponse;

@Service
public class AuthenticationServiceImpl<T> implements AuthenticationService<T> {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
	private Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	
	@Override
	public T doLogin(LoginRequest loginRequest) {
		if(loginRequest.getEmail() == null || loginRequest.getPassword() == null ) {
			logger.info("LoginService :  UserName and Password Can't be empty!!");
			return (T) new CommonResponse("UserName and Password Can't be empty!!", false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);	 
		}
		
		Employee employe = this.employeeRepository.findByEmail(loginRequest.getEmail());
		logger.info("LoginService : Login Employee : {}", employe);
		
		if(employe == null) {
			logger.info("LoginService : Employee not found !!");
			return (T) new CommonResponse("Employee not found!!", false,404, null);
		}
		
//		String requestEncodedPassword = this.passwordEncoder.encode(loginRequest.getPassword());
//		logger.info("LoginService:: RequestEncodedPassword:"+ requestEncodedPassword);
		if(!employe.getPassword().equals(loginRequest.getPassword())) {
			logger.info("LoginService : Password mismatch !!");
			return (T) new CommonResponse("invalid Password", false, 404, null);
		}
			
//		Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail() ,loginRequest.getPassword()));
//		logger.info("AUTHENTICATION-MANAGER-RESPONSE:"+ authenticate);
		
		logger.info("LoginService : Password matched !!");
//		UserDetails userDetails= new org.springframework.security.core.userdetails.User(loginRequest.getEmail(), loginRequest.getPassword(), true, true, true, true, employe.getRole().getAuthorities());
		
		String token = this.jwtTokenUtil.generateToken(employe);
		logger.info("LoginService : login Success : {}", employe);
		
		
		return (T) new LoginResponse(token, LocalDate.now(), employe);
	}

}
