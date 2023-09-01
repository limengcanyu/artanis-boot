package com.spring.boot.pulsar;

import io.github.majusko.pulsar.producer.ProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfiguration {

    @Bean
    public ProducerFactory producerFactory() {
        return new ProducerFactory()
                .addProducer("msg-topic", MyMsg.class)
                .addProducer("string-topic", String.class);
    }

}
