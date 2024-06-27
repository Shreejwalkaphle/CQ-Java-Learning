package com.example.Integration2.Controllers;

import com.example.Integration2.services.FileServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping()
public class TestController {

    @Autowired
    FileServices fileServices;
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    @CrossOrigin("*")
    public String hello() {
        // Log a debug message
        logger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first endpoint.";
    }

    @GetMapping("/sendFile")
    @CrossOrigin("*")
    public String sendFile() throws IOException {
        fileServices.createAndSendFile();
        return "file sent";
    }

//    yo receive file method le server ko kaam garcha. server side ma yo code wa yei code ko replication rakhna parcha.replication bhanna ley server java ma bhaye java
//    server aru language ma bhaye tei specific language ma yo endpoint hit huncha.
    @PostMapping("/receiveFile")
    public String receiveFile(@RequestBody byte[] requestData) {
        String receivedData = new String(requestData);
        System.out.println(receivedData);

        // Extract metadata and file content from combined data
        String[] parts = new String(requestData, StandardCharsets.UTF_8).split("\n", 4);

        String fileName = parts[0];
        String fileType = parts[1];
        long fileSize = Long.parseLong(parts[2]);
        String fileData = parts[3];
        logger.info("File received: " + fileName);
        logger.info("File type: " + fileType);
        logger.info("File size: " + fileSize + " bytes");
        logger.info("file data : " + fileData);
        return "file received";
    }
}
