package com.spring.boot.rocketmq.order.example;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * <p>
 * Send message sample code
 * </p>
 *
 * @author rock.jxf
 * @date 2020/11/21 11:36
 */
public class OrderedProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("example_group_name");

        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.81.129:9876");

        //Launch the instance.
        producer.start();

//        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        String[] tags = new String[]{"TagA"};

        for (int i = 0; i < 10; i++) {
            int orderId = i % 10;
            //Create a message instance, specifying topic, tag and message body.
//            Message msg = new Message("OrderedTopicTest", tags[i % tags.length], "KEY" + i,
            Message msg = new Message("OrderedTopicTest", tags[0], "KEY" + i,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderId);

            System.out.printf("%s%n", sendResult);
        }

        //server shutdown
        producer.shutdown();
    }
}
