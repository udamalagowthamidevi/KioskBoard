package com.hackathon.Restaurant.Auth;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hackathon.Restaurant.DTO.User;
import com.hackathon.Restaurant.Repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	

	@Autowired
	UserRepository userRepo;
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return  userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("No user"));
	}
	
	

}
