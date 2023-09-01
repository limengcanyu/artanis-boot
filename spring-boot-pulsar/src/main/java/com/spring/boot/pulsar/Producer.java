package com.spring.boot.pulsar;

import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private PulsarTemplate<MyMsg> producer;

    public void sendMyMsg() throws PulsarClientException {
        producer.send("msg-topic", new MyMsg("Hello world!"));
    }

}
