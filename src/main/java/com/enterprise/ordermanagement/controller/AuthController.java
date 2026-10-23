package com.enterprise.ordermanagement.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.ordermanagement.dto.LoginRequest;
import com.enterprise.ordermanagement.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authmanager;
	
	private final JwtService jwtService;

	public AuthController(AuthenticationManager authmanager, JwtService jwtservice) {
		
		this.authmanager = authmanager;
		this.jwtService =jwtservice;
	} 
	
	@PostMapping("/login")  
	public String login(@RequestBody LoginRequest request) {
		
		
			
			Authentication authentication = authmanager
					.authenticate(new UsernamePasswordAuthenticationToken(
							request.getUsername(), 
							request.getPassword())
				
							);
			
			System.out.println("Authentication = " + authentication);
			System.out.println("Authenticated = " + authentication.isAuthenticated());
			System.out.println("Principal = " + authentication.getPrincipal());
			System.out.println("Authorities = " + authentication.getAuthorities());
			
			String token = jwtService.generateToken(
			        (UserDetails) authentication.getPrincipal()
			);
			
			return token;
			
		}


}
