package com.spring.boot.pulsar;

import io.github.majusko.pulsar.annotation.PulsarConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    @PulsarConsumer(topic = "msg-topic", clazz = MyMsg.class)
    void consume(MyMsg msg) {
        log.debug("Consumer consume ......");
        log.debug("received msg: {}", msg.getData());
    }

}
