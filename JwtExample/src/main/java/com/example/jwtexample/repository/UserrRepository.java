package com.example.jwtexample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwtexample.model.Userr;

@Repository
public interface UserrRepository extends JpaRepository<Userr, Long>{

	public Optional<Userr> findByEmail(String email);
	
}
