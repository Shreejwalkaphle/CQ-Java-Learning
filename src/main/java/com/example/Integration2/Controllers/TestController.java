package com.example.Integration2.Controllers;

import com.example.Integration2.services.GoogleOAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping()
public class TestController {
    @Autowired
    GoogleOAuthService googleOAuthService;

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    @CrossOrigin("*")
    public String hello() {
        // Log a debug message
        logger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first endpoint.";
    }
    @GetMapping("/shreejwal/returnedfromgoogle")
    @CrossOrigin("*")
    public void getData( @RequestParam("code") String code, HttpServletResponse response ) throws IOException {
        // Log a debug message
        logger.info("Request received from angular at /getData endpoint.");
//        String code = request.getHeader( "code" );
        System.out.println("authorization code received from google is : => " + code);
        googleOAuthService.exchangeCodeForToken( code );
        // Redirect to Angular homepage
        String angularRedirectUrl = "http://localhost:4200/homepage"; // Update with your Angular app URL
        response.sendRedirect( angularRedirectUrl );

//        return String.valueOf( ResponseEntity.status( HttpStatus.FOUND).header("Location", angularRedirectUrl).build() );
    }
}
