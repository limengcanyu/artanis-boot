package com.spring.boot.pulsar;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarConsumer {
    public static void main(String[] args) throws PulsarClientException, InterruptedException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.242.129:6650")
                .build();

        // ###########################################################################################
        Consumer consumer = client.newConsumer()
                .topic("my-topic")
                .subscriptionName("my-subscription")
                .subscribe();

        while (true) {
            // Wait for a message
            Message msg = consumer.receive();

            try {
                // Do something with the message
                System.out.println("Message received: " + new String(msg.getData()));

                // Acknowledge the message so that it can be deleted by the message broker
                consumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }

        // ###########################################################################################
//        // If you don't want to block your main thread and rather listen constantly for new messages, consider using a MessageListener.
//        MessageListener myMessageListener = (consumer, msg) -> {
//            try {
//                System.out.println("Message received: " + new String(msg.getData()));
//                consumer.acknowledge(msg);
//            } catch (Exception e) {
//                consumer.negativeAcknowledge(msg);
//            }
//        };
//
//        Consumer consumer = client.newConsumer()
//                .topic("my-topic")
//                .subscriptionName("my-subscription")
//                .messageListener(myMessageListener)
//                .subscribe();
//
//        consumer.close();
//        client.close();
//
//        TimeUnit.SECONDS.sleep(60);
        // ###########################################################################################


    }
}
