package com.spring.boot.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    private SendingMessage sendingMessage;

    /**
     * <a href="http://localhost:8080/sendStringMessage">...</a>
     *
     * @return
     */
    @RequestMapping("/sendStringMessage")
    public String sendStringMessage() {
        sendingMessage.sendMessage(RabbitMQConst.QUEUE_NAME_STRING, "hello");
        return "send message: hello";
    }
}
