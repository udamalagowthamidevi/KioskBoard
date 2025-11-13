package com.hackathon.Restaurant.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.hackathon.Restaurant.DTO.JwtRequest;
import com.hackathon.Restaurant.DTO.JwtResponse;

@Service
public class AuthService {
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtHelper jwtHelper;

	public  ResponseEntity<JwtResponse> login(JwtRequest request) {
		this.doAuthenticate(request.getUsername(), request.getPassword());
		String token=jwtHelper.generateToken(request.getUsername());
		JwtResponse res=JwtResponse.builder().jwtToken(token).build();
		return ResponseEntity.ok(res);
		
	}
	
	public void doAuthenticate(String username,String password) {
		UsernamePasswordAuthenticationToken usernamepasswordauth=new UsernamePasswordAuthenticationToken(username,password);
		try {
			authManager.authenticate(usernamepasswordauth);
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username and password");
		}
	}
	
	

}
