package com.example.Integration2.services;

import com.example.Integration2.GoogleCredentialsConfig;
import com.example.Integration2.Util.Util;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class GoogleAuthService {

	private final GoogleCredentialsConfig oauthConfig;
	@Autowired
	GoogleDriveService googleDriveService;

	@Autowired
	public GoogleAuthService(GoogleCredentialsConfig oauthConfig) {
		this.oauthConfig = oauthConfig;
	}

	public GoogleAuthorizationCodeRequestUrl authenticate() throws IOException {
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance( );

		// Set up the GoogleAuthorizationCodeFlow
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				new NetHttpTransport( ),
				jsonFactory,
				oauthConfig.getClientId( ),
				oauthConfig.getClientSecret( ),
				Collections.singletonList( "https://www.googleapis.com/auth/drive.file" )
		).setAccessType( "offline" )
				.build( );

		// Generate the authorization URL
		String redirectUri = oauthConfig.getRedirectUri( );
		GoogleAuthorizationCodeRequestUrl authorizationUrl = flow
				.newAuthorizationUrl( )
				.setRedirectUri( redirectUri )
				.setAccessType("offline") // Request offline access for refresh tokens
				.setApprovalPrompt("force"); // Explicitly force user consent;

		System.out.println( "Visit this URL to authorize the application: " );
		System.out.println( authorizationUrl );
		return authorizationUrl;
	}

	public String exchangeTokenWith(String authorizationCode){
		try {
			JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

			// Set up the GoogleAuthorizationCodeFlow
			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
					new NetHttpTransport(),
					jsonFactory,
					oauthConfig.getClientId(),
					oauthConfig.getClientSecret(),
					Collections.singletonList("https://www.googleapis.com/auth/drive")
			).setAccessType("offline")
					.build();

			// Exchange the authorization code for tokens
			GoogleTokenResponse tokenResponse = flow.newTokenRequest(authorizationCode)
					.setRedirectUri(oauthConfig.getRedirectUri())
					.execute();


			String accessToken = tokenResponse.getAccessToken();
			String refreshToken = tokenResponse.getRefreshToken();

//			set access token and refresh token to Util for temporary storage.
			Util.setAccessToken( accessToken );
			Util.setRefreshToken( refreshToken );


			System.out.println("accessToken : "+accessToken );
			System.out.println("refreshToken : "+refreshToken );

			return accessToken;
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed to exchange authorization code for tokens.";
		}
	}

	}

