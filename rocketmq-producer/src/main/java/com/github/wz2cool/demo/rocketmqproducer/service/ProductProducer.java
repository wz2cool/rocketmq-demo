package com.github.wz2cool.demo.rocketmqproducer.service;

import com.github.wz2cool.demo.rocketmqproducer.model.Product;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductProducer {

    private static Logger logger = LoggerFactory.getLogger(ProductProducer.class);

    private static final String TOPIC = "demo-product";

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendProducts(Product... products) {
        if (ArrayUtils.isEmpty(products)) {
            return;
        }
        for (Product product : products) {
            SendResult sendResult = rocketMQTemplate.syncSendOrderly(TOPIC, product, product.getProductId() + "");
            logger.info("send demo-product topic result: {}", sendResult.toString());
        }
    }
}
