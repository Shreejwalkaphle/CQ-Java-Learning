package com.example.Integration2.AuthenticationAndAuthorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.util.Calendar;


@Component
public class SalesforceAPI {
    private static final String ORG_BASE_URL = "https://d5j00000dpppdeaj-dev-ed.develop.my.salesforce.com";

    // Salesforce OAuth endpoint
    private static final String PRODUCTION_AUTH_URL = "https://login.salesforce.com/services/oauth2/token"; // for production . this is not in use
    private static final String SANDBOX_AUTH_URL = "https://test.salesforce.com/services/oauth2/token"; // for sandbox . this is not in use
    private static final String TOKEN_URL_FOR_MY_CUSTOM_ORG= "https://d5j00000dpppdeaj-dev-ed.develop.my.salesforce.com/services/oauth2/token"; // for my custom org. this is in use
    private static final String AUTH_URL_FOR_MY_CUSTOM_ORG= "https://d5j00000dpppdeaj-dev-ed.develop.my.salesforce.com/services/oauth2/authorize";
    private static final String USERNAME = "shreejwalkaphle@gmail.com";
    private static final String PASSWORD = "Salesforce@123#";

    //Autowired the class. in this class i have variables to get the client id and client secret from the application.properties file
//    @Autowired
//    private SalesforceConfig salesforceConfig;

    //working for client crediantial oauth flow
//    public String authenticate() throws IOException, ParseException {
//
//        // Use the SalesforceConfig object to get consumer key and secret
//        String consumerKey = salesforceConfig.getConsumerKey();
//        String consumerSecret = salesforceConfig.getConsumerSecret();
//        // Construct token request body for grant type client crediantials since
//        String requestBody = "grant_type=client_credentials&client_id=" + consumerKey + "&client_secret=" + consumerSecret;
//        // Make a POST request to the OAuth token endpoint
//        URL url = new URL(TOKEN_URL_FOR_MY_CUSTOM_ORG);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        connection.setDoOutput(true);
//        connection.getOutputStream().write(requestBody.getBytes());
//        String response = new String(connection.getInputStream().readAllBytes());
//        System.out.println("this string contains access token : :  "+response);
//        return response;
//    }


    //for web server authorization code flow
//    public String authenticate() throws IOException, ParseException {
//
//        // Use the SalesforceConfig object to get consumer key and secret
//        String consumerKey = "3MVG9pRzvMkjMb6mtddWuRYo2LRHvwaBdEwkWZhQtSUuoriGnEOQp6A7aglZ6q6CsJ1eKtZ5DnjHx9Q2rKOx0";
//        // Construct token request body for grant type code
//        String requestBody = "response_type=code&client_id=" + consumerKey + "&redirect_uri=https://afd0-202-166-206-61.ngrok-free.app/hello/getauthorizationcode";
//        // Make a POST request to the OAuth token endpoint
//        URL url = new URL(AUTH_URL_FOR_MY_CUSTOM_ORG);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        connection.setDoOutput(true);
//        connection.getOutputStream().write(requestBody.getBytes());
//        String response = new String(connection.getInputStream().readAllBytes());
//        System.out.println("this string contains response from salesforce  : :  "+response);
//        return response;
//    }


     //for authorization code flow according to as2 // not working
    public String authenticate() throws IOException, ParseException {

        String consumerKey = "3MVG9pRzvMkjMb6mtddWuRYo2LRHvwaBdEwkWZhQtSUuoriGnEOQp6A7aglZ6q6CsJ1eKtZ5DnjHx9Q2rKOx0";
        // Construct url
        String url = AUTH_URL_FOR_MY_CUSTOM_ORG+"?response_type=code&client_id=" + consumerKey + "&redirect_uri="+URLEncoder.encode("https://afd0-202-166-206-61.ngrok-free.app/hello/getauthorizationcode" + "", "UTF-8");
        return url;
    }

    //approach 2 from as2
//    public String authenticate() throws IOException, ParseException {
//
//        // Use the SalesforceConfig object to get consumer key and secret
//        String consumerKey = salesforceConfig.getConsumerKey();
//        String consumerSecret = salesforceConfig.getConsumerSecret();
//        String redirectUri = salesforceConfig.getRedirectUri();
//
//
//        //make jwt
//        Algorithm signatureAlgorithm = Algorithm.HMAC256("My_secret_key"); // Replace with your preferred algorithm
//        String subject = "subjectShreejwal";
//        String issuer = "IssuerShreejwal";
//        String token = JWT.create()
//                .withIssuer(issuer)
//                .withSubject(subject)
//                .withIssuedAt(Calendar.getInstance().getTime())
//                .withClaim("username",USERNAME)
//                .withClaim("client_id",consumerKey)
//                .withClaim("audience",ORG_BASE_URL)
//                .sign(signatureAlgorithm);
////        System.out.println(token);
//
//        Form form = Form.form();
//        form.add("grant_type", "urn:ietf:params:oauth:grant-type:jwt-bearer");
//        form.add("assertion", token);
//        form.add("client_id",consumerKey );
//        form.add("client_secret", consumerSecret);
//        form.add("redirect_uri", redirectUri);
//
//        ClassicHttpResponse response = (ClassicHttpResponse) Request.post(TOKEN_URL_FOR_MY_CUSTOM_ORG)
//                .bodyForm(form.build()).execute().returnResponse();
//
//
//        String responseBody = EntityUtils.toString(response.getEntity());
//        System.out.println("this is access token : :  "+responseBody);
//        return responseBody;
//    }

    //approach 3 from youtube video
//    public String authenticate(){
////         Use the SalesforceConfig object to get consumer key and secret
//        String consumerKey = salesforceConfig.getConsumerKey();
//        String consumerSecret = salesforceConfig.getConsumerSecret();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        String loginurl ="http://a5.salesforce.com/services/oauth2/token"+"?grant_type=password&client_id=" + consumerKey + "&client_secret=" + consumerSecret + "&username=" + USERNAME + "&password=" + PASSWORD;
//        HttpPost httpPost = new HttpPost(loginurl);
//        HttpResponse response = null;
//        try{
//            response = httpClient.execute(httpPost);
//        }catch (ClientProtocolException e){
//        e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        final  int statusCode = response.getStatusLine().getStatusCode();
//        if(statusCode != HttpStatus.SC_OK){
//            System.out.println("Error authentication " + statusCode);
//            return "Error authentication " + statusCode ;
//        }
//        String getResult = null;
//        try{
//            getResult = EntityUtils.toString(response.getEntity());
//
//        }
//        catch (IOException e){
//        e.printStackTrace();
//        }
//        JSONObject jsonObject = null;
//        String logingAccessToken = null;
//        String loginInstanceUrl = null;
//        try{
//            jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
//            loginInstanceUrl = jsonObject.getString("access_token");
//            loginInstanceUrl= jsonObject.getString("instance_url");
//        }catch (JSONException e){e.printStackTrace();}
//        System.out.println(response.getStatusLine());
//        System.out.println("success login");
//        System.out.println("instance url : "+loginInstanceUrl);
//        System.out.println("access token = "+ logingAccessToken);
//        httpPost.releaseConnection();
//        return logingAccessToken;
//
//    }

    //approach 4 web authorization flow
//    public void authenticate() throws IOException, ParseException {
//        // Use the SalesforceConfig object to get consumer key and secret
//        String consumerKey = salesforceConfig.getConsumerKey();
//        String consumerSecret = salesforceConfig.getConsumerSecret();
//        String redirectUri = salesforceConfig.getRedirectUri();
//
//        // Construct authentication request body
//        String authorizationUrl = AUTH_URL_FOR_MY_CUSTOM_ORG + "?&client_id=" + consumerKey + "&redirect_uri=" + redirectUri + "&response_type=code";
//
//        // Make a GET request to the OAuth authorize endpoint
//        URL url = new URL(authorizationUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setDoOutput(true);
//        connection.getOutputStream().write(authorizationUrl.getBytes());
//    }

//
//    public String makeRequest(String accessToken) throws IOException {
//        // Make a GET request to the Salesforce REST API
//        URL url = new URL(ORG_BASE_URL+"/services/apexrest/api/getdata");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Authorization", "Bearer " + accessToken);
//
//        // Read the response
//        String response = new String(connection.getInputStream().readAllBytes());
//        System.out.println(response);
//        return response;
//    }


}
