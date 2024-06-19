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
//        producer le queue exchange sanga bind bhayeko key ko pattern pass garyo bhaney pani queue ma push garna paucha
//        exact jun key le bind bha ko ho tyei key chaidaina, mattern match bhaye ni huncha.
//        yaha pattern match bha cha kina ki queue bind huda "key.of" bhanera routing key le bind bha cha
//        tara aile hamile pathauda key.of.ram pathako cham  jasle "key.of" pattern lai match gareko cha.
        rabbitTemplate.convertAndSend(fromPropertiesFile.getExchangeName(), "key.of.ram", message);
        System.out.println("message produced from rabbit MQ : producer 1 '" + message + "'");

        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.getMessage();
        }

        System.out.println("i am awake");
    }

}
