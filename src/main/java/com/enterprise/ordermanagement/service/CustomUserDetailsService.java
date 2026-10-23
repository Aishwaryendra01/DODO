package com.enterprise.ordermanagement.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enterprise.ordermanagement.entity.User;
import com.enterprise.ordermanagement.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository ;
	

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("username not found" + username));
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername()).password(user.getPassword())
				.roles(user.getRole()).disabled(!user.isEnabled())
				.build();
	}

}
