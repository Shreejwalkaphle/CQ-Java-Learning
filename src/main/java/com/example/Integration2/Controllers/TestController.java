package com.example.Integration2.Controllers;

import com.example.Integration2.invokers.FanRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class TestController {

    @Autowired
    FanRemote fanRemote;

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    @CrossOrigin("*")
    public String hello() {
        // Log a debug message
        logger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first endpoint.";
    }

    @GetMapping("/givecommand")
    public String getCommand(@RequestParam String command) {
        String result=null;
        switch (command){
            case "on":{
                result= fanRemote.TurnOnFan();
                break;
            }
            case "off":{
                result=fanRemote.turnOffFan();
                break;
            }
            case "up":{
                result=fanRemote.increaseFanSpeed();
                break;
            }
            case "down":{
                result=fanRemote.decreaseFanSpeed();
                break;
            }
        }
        return result;
    }
}
