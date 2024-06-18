package com.example.Integration2.services;

import com.example.Integration2.Utility.PropertiesFileReader;
import com.example.Integration2.rabbitMQconfigurations.RabbitMQConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQMessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    PropertiesFileReader fromPropertiesFile;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(fromPropertiesFile.getExchangeName(), fromPropertiesFile.getRoutingKey(), message);
        System.out.println("message produced from rabbit MQ :  '" + message + "'");

        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.getMessage();
        }

        System.out.println("i am awake");
    }
}
