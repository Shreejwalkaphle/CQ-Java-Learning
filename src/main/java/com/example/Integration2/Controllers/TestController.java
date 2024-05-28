package com.example.Integration2.Controllers;

import com.example.Integration2.AuthenticationAndAuthorization.SalesforceAPI;
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

    //working with client crediantial oauth flow.
    @GetMapping("/authorizewithsalesforce")
    public void authorizeWithSalesforce() throws IOException, ParseException {
        logger.info("Request received at /hello/authorizewithsalesforce endpoint.");
        salesforceAPI.authenticate();
    }

}
