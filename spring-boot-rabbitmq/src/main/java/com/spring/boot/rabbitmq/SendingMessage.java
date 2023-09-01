package com.spring.boot.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendingMessage {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate template;

    public void sendMessage(String topic, String message) {
        template.convertAndSend(topic, message);
    }
}
