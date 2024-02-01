package com.example.jwtexample.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtexample.model.Userr;
import com.example.jwtexample.service.UserService;

@RestController
@RequestMapping("/home")
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public String home() {
		return "users";
	}
	
	@GetMapping("/getUser")
	public List<Userr> getUser(){
		return userService.getUser();
	}
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}
}
