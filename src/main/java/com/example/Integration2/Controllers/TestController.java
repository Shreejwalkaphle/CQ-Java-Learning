package com.example.Integration2.Controllers;

import com.example.Integration2.services.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class TestController {
    @Autowired
    CryptoService cryptoService;

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    @CrossOrigin("*")
    public String hello() {
        // Log a debug message
        logger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first endpoint.";
    }

    @GetMapping("/esewa")
    @CrossOrigin("*")
//    sender
    public String esewa() throws Exception {
        // Log a debug message
        logger.info("Request received at /esewa");
        String data = "hello";
        String encrypted = cryptoService.encryptAndSign( data );

        return "encrypted data : "+encrypted;
    }

    @PostMapping("/khalti")
    @CrossOrigin("*")
//    receiver
    public String khalti(@RequestParam("encryptedData") String encryptedData,
                         @RequestParam("signature") String signature) throws Exception {
        // Log a debug message
        logger.info("Request received at /khalti");
        String actualdata = cryptoService.verifyAndDecrypt( encryptedData,signature );
        return " actual data : "+actualdata;
    }

}
