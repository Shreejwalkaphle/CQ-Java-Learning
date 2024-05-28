package com.example.Integration2.AuthenticationAndAuthorization;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class SalesforceConfig {

    @Value("${salesforce.ac.consumer-key}")
    private String consumerKey;

    @Value("${salesforce.ac.consumer-secret}")
    private String consumerSecret;

    @Value("${salesforce.ac.callback-url}")
    private String callbackUrl;

    @Value("${REDIRECT_URI}")
    private String redirectUri;

    @Value("${ORG_BASE_URL}")
    private String orgBaseUrl;

    @Value("${NGROK_URL}")
    private String ngRokUrl;

    @Value("${AUTHORIZATION_URL}")
    private String authorizationUrl;

    @Value("${TOKEN_URL}")
    private String tokenUrl;

    @Value("${RESPONSETYPE}")
    private String responseType;

    @Value("${GRANTTYPE}")
    private String grantType;

}
