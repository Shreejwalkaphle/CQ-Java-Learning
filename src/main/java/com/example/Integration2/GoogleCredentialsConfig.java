package com.example.Integration2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleCredentialsConfig {

	@Value("${google.client-id}")
	private String clientId;

	@Value("${google.client-secret}")
	private String clientSecret;

	@Value("${google.auth-uri}")
	private String authUri;

	@Value("${google.token-uri}")
	private String tokenUri;

	@Value("${google.redirect-uri}")
	private String redirectUri;

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getAuthUri() {
		return authUri;
	}

	public String getTokenUri() {
		return tokenUri;
	}

	public String getRedirectUri() {
		return redirectUri;
	}
}
