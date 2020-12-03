package com.github.wz2cool.demo.rocketmqdemo.service;

import com.github.wz2cool.demo.rocketmqdemo.model.User;
import org.apache.rocketmq.client.MQAdmin;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProducerService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private MQAdmin mqAdmin;

    @Value(value = "${boot.rocketmq.topic}")
    private String springTopic;

    @Value(value = "${boot.rocketmq.topic.user}")
    private String userTopic;

    @Value(value = "${boot.rocketmq.tag}")
    private String tag;

    public SendResult sendString(String message) throws MQClientException {


        // 发送 String 类型的消息
        // 调用 RocketMQTemplate 的 syncSend 方法
        SendResult sendResult = rocketMQTemplate.syncSend(springTopic + ":" + tag, message);
        logger.info("syncSend String to topic {} sendResult={} \n", springTopic, sendResult);
        return sendResult;
    }

    public SendResult sendUser(User user) {
        // 发送 User
        SendResult sendResult = rocketMQTemplate.syncSend(userTopic, user);
        logger.info("syncSend User to topic {} sendResult= {} \n", userTopic, sendResult);
        return sendResult;
    }

}
