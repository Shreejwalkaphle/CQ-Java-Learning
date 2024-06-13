package com.example.Integration2.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Properties;

@Component
public class EmailSender {
    @Autowired
    JavaMailSender mailSender;
    public String sendEmail() throws MessagingException {
        String from = "shreejwalkaphle5@gmail.com";
        String to = "shreejwalkaphle@gmail.com";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Here's your pic");
        helper.setFrom(from);
        helper.setTo(to);

        String content = "<b>Dear guru</b>,<br><i>Please read : </i>"
                + "<br><b>Best Regards</b>";
        helper.setText(content, true);

        FileSystemResource resource = new FileSystemResource(new File("C:\\Users\\ShreejwalKaphle\\OneDrive - compliancequest.com\\Documents\\git testing concepts\\testfile.txt"));
        helper.addAttachment("testfile.txt", resource);
        mailSender.send(message);
        return "sent";
    }
}
