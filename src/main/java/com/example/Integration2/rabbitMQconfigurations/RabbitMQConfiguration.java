package com.example.Integration2.rabbitMQconfigurations;

import com.example.Integration2.Utility.PropertiesFileReader;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// there are always some issue related to RabbitAdmin while initializing
// what happens is if you use RabbitAdmin then it is configured in Rabbit message broker not in the spring context.
// the initialization of beans and declaration of queues do not syncronize step by step so there you can face issue .
// it works if you restart the rabbit server from docker and restart the program by stopping
// therefore to avoid these tedious task , i suggest to go for the beans type of declaration of queue , exchange and binding without using RabbitAdmin

@Configuration
public class RabbitMQConfiguration {

    @Autowired
    PropertiesFileReader fromPropertiesFile;
    @Autowired
    ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory);
    }

    public void declareQueueExchangesAndBindings(){
//        declare  queue
        Queue queue1 = new Queue(fromPropertiesFile.getQueueName1());
        rabbitAdmin().declareQueue(queue1);


//        declare a single directExchange
        TopicExchange yourExchange = new TopicExchange(fromPropertiesFile.getExchangeName());
        rabbitAdmin().declareExchange(yourExchange);

//        bind the queue with single Topic exchange
        Binding binding1 = BindingBuilder.bind(queue1).to(yourExchange).with(fromPropertiesFile.getRoutingKey1());
        rabbitAdmin().declareBinding(binding1);
    }
}