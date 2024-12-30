package com.example.Integration2.Controllers;

import com.example.Integration2.models.Users;
import com.example.Integration2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
public class UserController {
	@Autowired
	UserService userService;
	@PostMapping("/registerUser")
	@CrossOrigin("http://localhost:4200")
	public String setData(@RequestBody Users user) {
		return userService.saveData(user);
	}
	@PostMapping("/login")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity <Users> login( @RequestBody Users users) {
		Users user = userService.getUserByUsername(users.getUsername());
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status( HttpStatus.NOT_FOUND).body(null);
		}
	}
}
