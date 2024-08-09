//package com.example.Integration2.test;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
//
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
//@Component
//public class IdTokenValidator {
//
//	private static final String CLIENT_ID = "1094531298396-26cq219g5330791u1hurfh66og9456qo.apps.googleusercontent.com";
//
//	public static void validateIdToken(String idTokenString) {
//
//			HttpTransport httpTransport = new NetHttpTransport();
//			JsonFactory jsonFactory = new GsonFactory();
//
//			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
//					.setAudience(Collections.singletonList(CLIENT_ID))
//					.build();
//
//			// Use the verifier to verify the token
//
//	}
//}
