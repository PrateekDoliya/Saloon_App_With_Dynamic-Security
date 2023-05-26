package com.security.library.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.library.jwt.JwtAuthenticationEntryPoint;
import com.security.library.jwt.JwtRequestFilter;
import com.security.library.jwt.JwtUserDetailsService;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SpringSecurityConfig {

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.cors().disable()
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/api/v1/authenticate/signin").permitAll()
		.requestMatchers("/api/v1/employee/get-all").permitAll()
		.requestMatchers("/api/v1/employee/get/id/**").permitAll()
		.anyRequest()
		.authenticated().and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint).and()
		.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		
		return httpSecurity.build();
	}
	
	@Bean
	public DaoAuthenticationProvider provider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(this.passwordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
