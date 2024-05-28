package com.example.Integration2.AuthenticationAndAuthorization;

import org.apache.hc.core5.http.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class SalesforceAPI {

    //Autowired the class. in this class i have variables to get the client id and client secret and org base url etc from the application.properties file
    @Autowired
    private SalesforceConfig salesforceConfig;

    //this method will prepare me the required url to hit the authorization api of salesforce to get me the auth code
    public String authorize() throws IOException, ParseException {
        String consumerKey = salesforceConfig.getConsumerKey();
        String responseType = salesforceConfig.getResponseType();
        String orgBaseUrl = salesforceConfig.getOrgBaseUrl();
        String authorizationUrl = salesforceConfig.getAuthorizationUrl();
        String clientSecret = salesforceConfig.getConsumerSecret();
//        String redirectUri = salesforceConfig.getRedirectUri();
        String redirectUri = salesforceConfig.getNgRokUrl()+salesforceConfig.getCallbackUrl();

        // Construct url to hit in the salesforce along with redirect url for login.
        String requestUrl = orgBaseUrl + authorizationUrl + "?response_type=" + responseType + "&client_id=" + consumerKey + "&client_secret=" + clientSecret + "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8");
        return requestUrl;
    }

    //working for oauth  authorization code flow
    public void requestForToken(String code) throws IOException {
        String consumerKey = salesforceConfig.getConsumerKey();
        String consumerSecret = salesforceConfig.getConsumerSecret();
        String grantType = salesforceConfig.getGrantType();
//        String redirectUri = salesforceConfig.getRedirectUri();
        String redirectUri = salesforceConfig.getNgRokUrl()+salesforceConfig.getCallbackUrl();

        // Construct token request body
        String requestBody = "grant_type=" + grantType +
                "&client_id=" + URLEncoder.encode(consumerKey, "UTF-8") +
                "&client_secret=" + URLEncoder.encode(consumerSecret, "UTF-8") +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
                "&code=" + URLEncoder.encode(code, "UTF-8");

        // Make a POST request to the OAuth token endpoint
        URL url = new URL(salesforceConfig.getOrgBaseUrl() + salesforceConfig.getTokenUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        // Write the request body
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(requestBody.getBytes());
        }

        // Get the response
        int responseCode = connection.getResponseCode();
        InputStreamReader streamReader;
        if (responseCode == 200) {
            streamReader = new InputStreamReader(connection.getInputStream());
        } else {
            streamReader = new InputStreamReader(connection.getErrorStream());
        }

        //response inputstream ma aucha. inputstream JSON ma change garna paile hamile
        // inputstream bata data lai bufferedReader le ek ek byte ma linchamma
        // ek ek byte lai string ma change garcham
        try (BufferedReader reader = new BufferedReader(streamReader)) {
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            String response = responseBuilder.toString();

            if (responseCode == 200) {
                System.out.println("Response: " + response);
                // string lai JSON ma change garcham
                JSONObject jsonResponse = new JSONObject(response);
                String accessToken = jsonResponse.getString("access_token");
                System.out.println("Access Token: " + accessToken);
            } else {
                System.out.println("Error response: " + response);
            }
        } finally {
            connection.disconnect();
        }
    }
}

