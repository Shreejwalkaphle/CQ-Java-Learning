package com.example.Integration2.Controllers;

import com.example.Integration2.services.RabbitMQMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping()
public class TestController {

    @Autowired
    RabbitMQMessageProducer rabbitMQMessageProducer;
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    @CrossOrigin("*")
    public String hello() {
        // Log a debug message
        logger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first endpoint.";
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message1,@RequestParam String message2,@RequestParam String message3) {
        rabbitMQMessageProducer.sendMessage1(message1);
        rabbitMQMessageProducer.sendMessage2(message2);
        rabbitMQMessageProducer.sendMessage3(message3);

        return "Message sent: " + message1 + "Message sent: " + message2 + "Message sent: " + message3;
    }
}
