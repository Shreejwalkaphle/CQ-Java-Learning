package com.example.Integration2.AuthenticationAndAuthorization;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
/*
* this class fetches the configuration details from application.properties file
*/
@Getter
@Configuration
public class SalesforceConfig {

    @Value("${salesforce.cc.consumer-key}")
    private String consumerKey;

    @Value("${salesforce.cc.consumer-secret}")
    private String consumerSecret;

    @Value("${TOKEN_URL}")
    private String tokenUrl;

    @Value("${ORG_BASE_URL}")
    private String orgBaseUrl;

    @Value("${GRANT_TYPE}")
    private String grantType;

    @Value("${COMMON_REST_RESOURCES_ENDPOINT}")
    private String commonRestEndpoint;

}
