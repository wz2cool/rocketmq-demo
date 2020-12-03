package com.github.wz2cool.demo.rocketmqdemo.service;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RocketMQMessageListener(topic = "product_order", consumerGroup = "product_order_consumer", consumeMode = ConsumeMode.ORDERLY)
@Service
public class ProductOrderConsumer implements RocketMQListener<String> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(String message) {
        // 重写消息处理方法
        logger.info("------- ProductOrderConsumer received:{} \n", message);
        // TODO 对消息进行处理，比如写入数据
    }
}