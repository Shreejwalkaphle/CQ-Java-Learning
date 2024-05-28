package com.example.Integration2.Controllers;

import com.example.Integration2.AuthenticationAndAuthorization.SalesforceAPI;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
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



// working with client crediantial oauth flow.
//    @GetMapping("/authorizewithsalesforce")
//    public void authorizeWithSalesforce() throws IOException, ParseException {
//        logger.info("Request received at /hello/authorizewithsalesforce endpoint.");
//        salesforceAPI.authenticate();
//    }

    /*
    OAuth 2.0 Web Server Flow

    euta endpoint huna parcha jasle salesforce ko OAUTH-authoraization api hit garcha
    salesforce ko OAUTH-authorization api is: https://login.salesforce.com/services/oauth2/authorize
    yo api le authorization code pathaucha in response.
    authorization code hamile diyeko connected app ma bhayeko call back url ma pathaucha.
    tyo jun url ma authorization code aucha tya bata feri salesforce ko token api ma hit garna parcha by passing
    the received authorization code
    salesforce ko OAUTH-token api : https://login.salesforce.com/services/oauth2/token
    salesforce ko OAUTH-token api le hamilai access token dincha
    basically hamile authorization code lai access token sanga exchange gareko ho.
    access token payepachi hamile salesforce ma bhayeko rest-endpoints haru ma hit garna parney huncha.
    but each time we need to pass the access token while hitting the rest-endpoints of salesforce.
    * */


    @RequestMapping("/authorizewithsalesforce")
    public String authorizeWithSalesforce() throws IOException, ParseException {
        // Redirect to a view after authentication
        logger.debug("request to get code");
         String url = salesforceAPI.authenticate();
        return "redirect:"+url;
    }

    @PostMapping("/getauthorizationcode")
    @ResponseBody
    public void getAuthorizationCode(HttpServletRequest request) throws IOException {
        logger.debug("hello i am hit with code");
        String code = request.getParameter("code");
        logger.debug("The code received from authorization is : " + code);
//        String accessToken = salesforceAPI.makeRequest("!AQkAQGzW980FS5FTnGhoD8qKZ5VvUjzCPrWO6rlcuuZ3wUXE_Eo8pthOQrxWFlNgEPlvJ2wfpf6XwwZ4ARSR_ufgoZ_A_LjC");
//        logger.info("data from salesforce is :  " + accessToken);
//        return "data from salesforce is: : " + accessToken;
    }

//    @PostMapping("/test")
//    @ResponseBody
//    public int test() throws IOException {
//        return salesforceAPI.test();
//    }
}
