package com.hackathon.Restaurant.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hackathon.Restaurant.DTO.User;
import com.hackathon.Restaurant.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public  void saveUser(User user) {
		BCryptPasswordEncoder end=new BCryptPasswordEncoder();
		String encodedpassword=end.encode(user.getPassword());
		user.setPassword(encodedpassword);
		userRepo.save(user);
		
		
	}
	public List<User> getAllusers(){
		return userRepo.findAll();
	}

}
