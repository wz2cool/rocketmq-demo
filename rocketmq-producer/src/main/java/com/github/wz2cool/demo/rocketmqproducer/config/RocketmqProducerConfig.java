package com.github.wz2cool.demo.rocketmqproducer.config;

import org.apache.rocketmq.client.MQAdmin;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class RocketmqProducerConfig {

    @Resource
    private MQAdmin mqAdmin;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @PostConstruct
    public void createTopics() throws MQClientException {
        mqAdmin.createTopic(rocketMQTemplate.getProducer().getCreateTopicKey(), "demo_product", 10);
    }
}
