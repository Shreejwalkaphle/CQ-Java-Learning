package com.example.Integration2.services;

import com.example.Integration2.Utility.PropertiesFileReader;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQMessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    PropertiesFileReader fromPropertiesFile;

//    it will push the message to the queue 1 because we are passing the routing key that has binded the queue1 to the direct exchange.
    public void sendMessage1(String message) {
        rabbitTemplate.convertAndSend(fromPropertiesFile.getExchangeName(), fromPropertiesFile.getRoutingKey1(), message);
        System.out.println("message produced from rabbit MQ : producer 1 '" + message + "'");

        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.getMessage();
        }

        System.out.println("i am awake");
    }

    //    it will push the message to the queue2  because we are passing the routing key that has binded the queue2 to the direct exchange.
    public void sendMessage2(String message) {
        rabbitTemplate.convertAndSend(fromPropertiesFile.getExchangeName(), fromPropertiesFile.getRoutingKey2(), message);
        System.out.println("message produced from rabbit MQ : producer 2 '" + message + "'");

        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.getMessage();
        }

        System.out.println("i am awake");
    }

    //    it will push the message to the queue3 because we are passing the routing key that has binded the queue3 to the direct exchange.
    public void sendMessage3(String message) {
        rabbitTemplate.convertAndSend(fromPropertiesFile.getExchangeName(), fromPropertiesFile.getRoutingKey3(), message);
        System.out.println("message produced from rabbit MQ : producer 3 '" + message + "'");

        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.getMessage();
        }

        System.out.println("i am awake");
    }
}
