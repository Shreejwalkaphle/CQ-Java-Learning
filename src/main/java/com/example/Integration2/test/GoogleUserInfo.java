package com.example.Integration2.test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class GoogleUserInfo {

	private static final String USER_INFO_URL = "https://openidconnect.googleapis.com/v1/userinfo";

	// Method to fetch user info using access token
	public JsonNode fetchGoogleUserInfo(String accessToken) {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(USER_INFO_URL))
					.header("Authorization", "Bearer " + accessToken)
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// Parse the response to JSON
			ObjectMapper mapper = new ObjectMapper();
			JsonNode userInfo = mapper.readTree(response.body());

			return userInfo;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void registerUserWithGoogle(String accessToken) {
		JsonNode userInfo = fetchGoogleUserInfo(accessToken);

		if (userInfo != null) {
			String email = userInfo.get("email").asText();
			String firstName = userInfo.get("given_name").asText();
			String lastName = userInfo.get("family_name").asText();
			String picture = userInfo.get("picture").asText();

			// Use these details to register the user
			// Store the information in your database
			System.out.println("Email: " + email);
			System.out.println("First Name: " + firstName);
			System.out.println("Last Name: " + lastName);
			System.out.println("Picture URL: " + picture);

			// Continue with registration logic
		} else {
			System.out.println("Failed to fetch user information from Google.");
		}
	}
}

