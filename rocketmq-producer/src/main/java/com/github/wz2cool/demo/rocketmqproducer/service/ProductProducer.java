package com.github.wz2cool.demo.rocketmqproducer.service;

import com.github.wz2cool.demo.rocketmqproducer.model.Product;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductProducer {

    private static Logger logger = LoggerFactory.getLogger(ProductProducer.class);

    private static final String TOPIC = "batch_demo_product";

    @Resource
    private RocketMQTemplateService rocketMQTemplateService;

    public void sendProducts(Product... products) {
        if (ArrayUtils.isEmpty(products)) {
            return;
        }
        long startTime = System.currentTimeMillis();
        List<Message<Product>> collect = Arrays.stream(products).map(x -> MessageBuilder.withPayload(x).build()).collect(Collectors.toList());
        SendResult sendResult = rocketMQTemplateService.syncSendOrderly(TOPIC, collect, "2", 1000);
        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;
        String msg = String.format("take time: %d, success: %s", diff, sendResult.getMsgId());
        System.out.println(msg);
    }
}
