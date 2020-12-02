package com.github.wz2cool.demo.rocketmqdemo.service;

import com.github.wz2cool.demo.rocketmqdemo.model.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(nameServer = "${boot.rocketmq.NameServer}", topic = "${boot.rocketmq.topic.user}", consumerGroup = "user_consumer")
public class UserConsumer implements RocketMQListener<User> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onMessage(User user) {
        logger.info("######## user_consumer received: {} ; age: {} ; name: {} \n", user,user.getUserAge(),user.getUserName());
        // TODO 对消息进行处理User
    }
}
