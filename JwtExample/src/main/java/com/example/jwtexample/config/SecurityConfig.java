package com.example.jwtexample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwtexample.security.JwtAuthenticationEntryPoint;
import com.example.jwtexample.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
	
    @Autowired
    private JwtAuthenticationFilter filter;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
    	authenticationProvider.setUserDetailsService(userDetailsService);
    	authenticationProvider.setPasswordEncoder(passwordEncoder);
    	return authenticationProvider;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	http
    		.csrf(csrf->csrf.disable())
    		.cors(cors->cors.disable())
    		.authorizeHttpRequests(auth->auth.requestMatchers("/home/**").authenticated()
    				.requestMatchers("/auth/login")
    				.permitAll().requestMatchers("/auth/createUser").permitAll().anyRequest().authenticated())
    		.exceptionHandling(ex->ex.authenticationEntryPoint(point))
    		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	
    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    	
    	return http.build();
    }

}