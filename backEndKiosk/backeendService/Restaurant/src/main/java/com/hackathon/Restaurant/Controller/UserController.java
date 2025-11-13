package com.hackathon.Restaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.Restaurant.Auth.AuthService;
import com.hackathon.Restaurant.DTO.JwtRequest;
import com.hackathon.Restaurant.DTO.JwtResponse;
import com.hackathon.Restaurant.DTO.User;
import com.hackathon.Restaurant.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/register")
	public void createUser(@RequestBody User user) {
		userService.saveUser(user);
		
	}
	
	@GetMapping("/AllUsers")
		public List<User> findAllUsers(){
			return userService.getAllusers();
		}
	
	@GetMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		return authService.login(request);
			
		}
		
	}
	


