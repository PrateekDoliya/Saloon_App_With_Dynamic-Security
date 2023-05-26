package com.security.library.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.library.jwt.util.JwtTokenUtil;
import com.security.library.payloads.SharedData;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getRequestURI();
		return path.equals("/api/v1/authenticate/signin");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("JwtRequestFilter Called");

		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			log.info("inside requestTokenHeader");
			jwtToken = requestTokenHeader.substring(7);
				
			try {
				username = this.jwtTokenUtil.extractUsername(jwtToken);

			} catch (Exception e) {
				// TODO: handle exception
			}

//			System.out.println("USERNAME:" +username);
			log.info("USERNAME:" + username);
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
//			log.info("USER-DETAILS:" +userDetails);

//			log.info("AuthoritiesFromUserDetails:"+userDetails.getAuthorities());

//			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				log.info("inside username!=null");

				log.info("JWTREUESTFILTER:: AUTHORITIES"+ userDetails.getAuthorities());
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
//				logger.info("usernamePasswordAuthenticationToken:"+usernamePasswordAuthenticationToken);
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

				SharedData.setData("jwtToken", "Bearer "+jwtToken);

			}

		}
		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			// do nothing
		}

	}

}
