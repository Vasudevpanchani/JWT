package com.example.jwtexample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwtexample.model.Userr;
import com.example.jwtexample.repository.UserrRepository;

@Service
public class UserService {
	
	@Autowired
	private UserrRepository userrRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<Userr> getUser(){
		return userrRepository.findAll();
	}
	
	public Userr createUser(Userr userr) {
		userr.setPassword(passwordEncoder.encode(userr.getPassword()));
		return userrRepository.save(userr);
	}

}
