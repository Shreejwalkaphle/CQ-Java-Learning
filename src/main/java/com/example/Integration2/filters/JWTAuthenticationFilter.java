package com.example.Integration2.filters;

import com.example.Integration2.Util.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	private static final Key SECRET_KEY = Util.getSECRET_KEY();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Check the origin of the request
		String origin = request.getHeader("Origin");
		String referer = request.getHeader("Referer");

		// Define the allowed origin (your Angular application's base URL)
		String allowedOrigin = "http://localhost:4200"; // Update this to match your Angular app URL

		// Check if the request is from your Angular application
		if ((origin != null && origin.equals(allowedOrigin)) ||
				(referer != null && referer.startsWith(allowedOrigin))) {

			String authHeader = request.getHeader("Authorization");

			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				String token = authHeader.substring(7); // Remove "Bearer " prefix
				System.out.println("Token: " + token);
				try {
					// Build the JwtParser and parse the claims
					Claims claims = Jwts.parser()
							.setSigningKey(SECRET_KEY)
							.build()
							.parseClaimsJws(token)
							.getBody();

					// Retrieve claims (e.g., username) if needed
					String username = claims.getSubject();
					request.setAttribute("username", username);

				} catch (Exception e) {
					// If the token is invalid or expired
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return;
				}
			}
		}

		// Proceed to the next filter in the chain
		filterChain.doFilter(request, response);
	}
}
