package com.example.Integration2.services;

import com.example.Integration2.models.Users;
import com.example.Integration2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public String saveData(Users user){
		userRepository.save(user);
		System.out.println("user saved to db" );
		return "user"+ user +" saved successfully";
	}
	public Users getUserByUsername(String username) {
		return userRepository.findByUsername(username); // Use the repository method
	}



}