package com.spring.boot.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Exchange customDirectExchange() {
        return new DirectExchange(RabbitMQConst.CUSTOM_DIRECT_EXCHANGE_NAME, false, false);
    }
    @Bean
    public Queue stringQueue() {
        return new Queue(RabbitMQConst.QUEUE_NAME_STRING, false);
    }

    @Bean
    public Binding customBinding() {
        return new Binding(RabbitMQConst.QUEUE_NAME_STRING, Binding.DestinationType.EXCHANGE, RabbitMQConst.CUSTOM_DIRECT_EXCHANGE_NAME, "", null);
    }

}
