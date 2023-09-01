package com.spring.boot.pulsar;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

public class PulsarProducer {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.242.129:6650")
                .build();

        Producer<String> stringProducer = client.newProducer(Schema.STRING)
                .topic("my-topic")
                .create();
        stringProducer.send("My message");

        stringProducer.close();
        client.close();
    }
}
