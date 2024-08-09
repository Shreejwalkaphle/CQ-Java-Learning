package com.example.Integration2.services;
import com.example.Integration2.test.GoogleUserInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.net.URLEncodedUtils;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Service
public class GoogleOAuthService {

	@Autowired
	GoogleUserInfo googleUserInfo;

	public String exchangeCodeForToken(String code) throws IOException {
		String tokenUrl = "https://oauth2.googleapis.com/token";

		// Create the request body using NameValuePair
		List<NameValuePair> formParameters = new ArrayList<>();
		formParameters.add(new BasicNameValuePair("code", code));
		formParameters.add(new BasicNameValuePair("client_id", "1094531298396-26cq219g5330791u1hurfh66og9456qo.apps.googleusercontent.com"));
		formParameters.add(new BasicNameValuePair("client_secret", "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"));
		formParameters.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/shreejwal/returnedfromgoogle"));
		formParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));

		// Convert the form parameters to a URL-encoded string
		String form = URLEncodedUtils.format(formParameters, ContentType.APPLICATION_FORM_URLENCODED.getCharset());

		// Create HttpClient
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			// Create POST request
			HttpPost httpPost = new HttpPost(tokenUrl);
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.setEntity(new StringEntity(form, ContentType.APPLICATION_FORM_URLENCODED));

			// Execute request
			try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
				// Return the response body
				String str = EntityUtils.toString(response.getEntity());
				System.out.println(str );
				// Parse JSON response to extract access token
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonResponse = mapper.readTree(str);
				String accesstoken = jsonResponse.get("access_token").asText();
				googleUserInfo.registerUserWithGoogle( accesstoken );
				return str;
			} catch ( ParseException e ) {
				throw new RuntimeException( e );
			}
		}
	}
}
