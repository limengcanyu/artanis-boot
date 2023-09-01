package com.spring.boot.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProducerController {

    @Autowired
    private Producer producer;

    /**
     * http://localhost:8080/produceMsg
     *
     * @throws PulsarClientException
     */
    @RequestMapping("/produceMsg")
    public void produceMsg() throws PulsarClientException {
        producer.sendMyMsg();
    }

}
