package com.example.Integration2.services;

import com.example.Integration2.Util.Util;
import com.example.Integration2.models.Token;
import com.example.Integration2.models.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
	// Generate a secure key for signing the JWT
//	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final Key SECRET_KEY = Util.getSECRET_KEY();

	// Method to generate JWT token
	public Token generateJWTToken( Users user) {
		long now = System.currentTimeMillis();
		long expirationTime = 1000 * 60 * 60 * 1; // Token valid for 1 hour

		String jwt =  Jwts.builder()
				.setSubject(user.getUsername())  // Set the user information (payload)
				.setIssuedAt(new Date(now))  // Set the issue date
				.setExpiration(new Date(now + expirationTime))  // Set expiration time
				.signWith(SECRET_KEY)  // Use the secure key for signing
				.compact();  // Create the token string
		Token token = new Token();
		token.setToken( jwt );
		return token;

	}
}
