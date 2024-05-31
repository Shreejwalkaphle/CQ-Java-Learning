package com.example.Integration2.AuthenticationAndAuthorization;

import org.apache.hc.core5.http.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
@Component
public class SalesforceAPI {

    //Autowired the class. in this class i have variables to get the client id and client secret from the application.properties file
    @Autowired
    private SalesforceConfig salesforceConfig;

    //working for client crediantial oauth flow
    public void authenticate() throws IOException, ParseException {

        // Use the SalesforceConfig object to get consumer key and secret and grantType and baserUrlofOrg and TokenUrl
        String consumerKey = salesforceConfig.getConsumerKey();
        String consumerSecret = salesforceConfig.getConsumerSecret();
        String grantType = salesforceConfig.getGrantType();
        String baseUrl = salesforceConfig.getOrgBaseUrl();
        String tokenUrl = salesforceConfig.getTokenUrl();
        // Construct token request body for grant type client credentials
        String requestBody = "grant_type="+grantType+"&client_id=" + consumerKey + "&client_secret=" + consumerSecret;
        // Make a POST request to the OAuth token endpoint
        URL url = new URL(baseUrl+tokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);
        connection.getOutputStream().write(requestBody.getBytes());
        String response = new String(connection.getInputStream().readAllBytes());
        System.out.println("this string contains access token : :  "+response);

        JSONObject jsonResponse = new JSONObject(response);
        String accessToken = jsonResponse.getString("access_token");

        System.out.println("Access Token: " + accessToken);
        makeRequestToSalesforce(accessToken);
    }

    public void makeRequestToSalesforce(String accessToken){
        try {
            URL url = new URL(salesforceConfig.getOrgBaseUrl()+salesforceConfig.getCommonRestEndpoint()+"/giveData");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken); // Add the correct access token
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
