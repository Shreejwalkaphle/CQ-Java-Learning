package com.example.Integration2.rabbitMQconfigurations;

import com.example.Integration2.Utility.PropertiesFileReader;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Autowired
    PropertiesFileReader fromPropertiesFile;

    @Bean
    public Queue yourQueue() {
        return new Queue(fromPropertiesFile.getQueueName(), false);
    }

    @Bean
    public TopicExchange yourExchange() {
        return new TopicExchange(fromPropertiesFile.getExchangeName());
    }

    @Bean
    public Binding binding(Queue yourQueue, TopicExchange yourExchange) {
        return BindingBuilder.bind(yourQueue).to(yourExchange).with(fromPropertiesFile.getRoutingKey());
    }
}