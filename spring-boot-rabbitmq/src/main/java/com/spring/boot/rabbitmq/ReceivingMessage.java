package com.spring.boot.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceivingMessage {

    @RabbitListener(queues = RabbitMQConst.QUEUE_NAME_STRING)
    public void processMessage(String content) {
        System.out.println(RabbitMQConst.QUEUE_NAME_STRING + " received: " + content);
    }

}
