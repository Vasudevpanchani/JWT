package com.example.jwtexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwtexample.model.Userr;
import com.example.jwtexample.repository.UserrRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserrRepository userrRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userr user = userrRepository.findByEmail(username).orElseThrow(()->new RuntimeException("User not found!!"));
		return user;
	}

}
