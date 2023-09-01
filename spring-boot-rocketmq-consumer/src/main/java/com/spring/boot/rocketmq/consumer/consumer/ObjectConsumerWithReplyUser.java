/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spring.boot.rocketmq.consumer.consumer;

import com.spring.boot.rocketmq.consumer.entity.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Service;

/**
 * The consumer that replying Object
 */
@Service
//@RocketMQMessageListener(topic = "${demo.rocketmq.objectRequestTopic}", consumerGroup = "${demo.rocketmq.objectRequestConsumer}", selectorExpression = "${demo.rocketmq.tag}")
public class ObjectConsumerWithReplyUser implements RocketMQReplyListener<User, User> {

    @Override
    public User onMessage(User user) {
        System.out.printf("------- ObjectConsumerWithReplyUser received: %s \n", user);
        User replyUser = new User();
        replyUser.setUserAge((byte) 10);
        replyUser.setUserName("replyUserName");
        return replyUser;
    }
}
