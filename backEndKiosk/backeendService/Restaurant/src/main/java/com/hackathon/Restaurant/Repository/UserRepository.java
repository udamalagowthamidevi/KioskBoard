package com.hackathon.Restaurant.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.Restaurant.DTO.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	Optional<User> findByUsername(String username);

}
