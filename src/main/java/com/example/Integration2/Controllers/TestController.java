package com.example.Integration2.Controllers;

import com.example.Integration2.services.EmailSender;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    EmailSender emailSender;
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    public String hello() {
        // Log a debug message
        logger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first endpoint.";
    }

    @GetMapping("/sendEmail")
    public String sendEmail() throws MessagingException {
        logger.info("send email");
        logger.debug("semd email");
        String response = emailSender.sendEmail();
        return response;
    }
}
