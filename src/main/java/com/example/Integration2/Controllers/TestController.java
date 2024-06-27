package com.example.Integration2.Controllers;

import com.example.Integration2.services.FileServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
public ResponseEntity<String> handleFileUpload(@RequestBody byte[] fileData) {
    if (fileData == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
    }
    String receiveddata = new String(fileData);
    System.out.println(receiveddata);
    return ResponseEntity.ok("File uploaded successfully: " + receiveddata);
}

}
