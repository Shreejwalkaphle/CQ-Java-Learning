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


//about fanout exchannge
//When a message is sent to a Fanout exchange, it is copied and delivered to every queue bound to that exchange.
// This means all queues receive a copy of every message sent to the exchange, regardless of any routing keys or patterns.
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
//        declare  queue1
        Queue queue1 = new Queue(fromPropertiesFile.getQueueName1());
        rabbitAdmin().declareQueue(queue1);

//        declare  queue2
        Queue queue2 = new Queue(fromPropertiesFile.getQueueName2());
        rabbitAdmin().declareQueue(queue2);


//        declare a single fanoutExchange
        FanoutExchange yourExchange = new FanoutExchange(fromPropertiesFile.getExchangeName());
        rabbitAdmin().declareExchange(yourExchange);

//        bind all the queue with single Fanout exchange. fanout exchange ignores the routing key totally. because it broadcast to all the queue. not to  specific queue
        Binding binding1 = BindingBuilder.bind(queue1).to(yourExchange);
        rabbitAdmin().declareBinding(binding1);
        Binding binding2 = BindingBuilder.bind(queue2).to(yourExchange);
        rabbitAdmin().declareBinding(binding2);
    }
}