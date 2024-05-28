package com.example.Integration2.Controllers;

import com.example.Integration2.AuthenticationAndAuthorization.SalesforceAPI;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/hello")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private SalesforceAPI salesforceAPI;
    @GetMapping("/sayHello")
    @CrossOrigin("*")
    public String hello() {
        // Log a debug message
        logger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first endpoint.";
    }


    /*
    OAuth 2.0 Web Server Flow

    euta endpoint huna parcha jasle salesforce ko OAUTH-authoraization api hit garcha
    salesforce ko OAUTH-authorization api is: org_ko_base_url/services/oauth2/authorize
    yo api ma hamile hit haney pachi login page ma faldincha hamilai.
    loign page ma login success garna parcha hamile.
    login success garepachi hamilai salesforce le hit hancha.
    salesforce lai hamile callback url ma bhaneko huncham yo url ma hit han malai bhanera. plus yo kura hamile authorization code ko lagi request garda pani as a redirect uri hamle mention gardeko huncham
    login success bhayo bhaney salesforce le authorization code pathaucha in response.
    authorization code hamile diyeko connected app ma bhayeko call back url ma pathaucha.
    tyo jun url ma authorization code aucha tya bata feri salesforce ko token api ma hit garna parcha by passing
    the received authorization code
    salesforce ko OAUTH-token api : org_ko_base_url/services/oauth2/token
    salesforce ko OAUTH-token api le hamilai access token dincha
    basically hamile authorization code lai access token sanga exchange gareko ho.
    access token payepachi hamile salesforce ma bhayeko rest-endpoints haru ma hit garna parney huncha.
    but each time we need to pass the access token while hitting the rest-endpoints of salesforce.
    * */


    @RequestMapping("/authorizewithsalesforce")
    public void authorizeWithSalesforce(HttpServletResponse response) throws IOException, ParseException {
        // Redirect to a view after authentication
        logger.debug("request to get code");
        String url = salesforceAPI.authorize();
        response.sendRedirect(url);
    }

    @RequestMapping("/getauthorizationcode")
    @ResponseBody
    public void getAuthorizationCode(HttpServletRequest request) throws IOException, ParseException {
        logger.debug("hello i am hit with code");
        logger.info("hello i am hit with code");
        String code = request.getParameter("code");
        logger.debug("The code received from authorization is : " + code);
        logger.info("The code received from authorization is : " + code);
        salesforceAPI.requestForToken(code);

    }

}
