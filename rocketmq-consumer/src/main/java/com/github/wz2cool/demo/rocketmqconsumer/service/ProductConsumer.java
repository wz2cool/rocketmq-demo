package com.github.wz2cool.demo.rocketmqconsumer.service;

import com.github.wz2cool.demo.rocketmqconsumer.model.Product;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "demo_product", consumerGroup = "demo_product_consumerGroup", consumeMode = ConsumeMode.ORDERLY)
public class ProductConsumer implements RocketMQListener<Product> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Product message) {
        logger.info("receive product: {}, create_time: {}", message.getProductId(), message.getCreateTime());
    }
}
