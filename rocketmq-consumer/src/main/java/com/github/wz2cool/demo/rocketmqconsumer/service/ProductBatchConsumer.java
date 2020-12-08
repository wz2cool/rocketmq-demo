package com.github.wz2cool.demo.rocketmqconsumer.service;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RocketMQMessageListener(topic = "demo_product", consumerGroup = "demo_batch_product_consumerGroup", consumeMode = ConsumeMode.ORDERLY)
public class ProductBatchConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(String message) {
        // do noting, have to implement RocketMQListener or throw error
    }

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setPullInterval(1000);
        consumer.setConsumeThreadMin(1);
        consumer.setConsumeThreadMax(1);
        consumer.setConsumeMessageBatchMaxSize(1000);
        consumer.setPullBatchSize(100);
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {

            for (MessageExt message : messages) {
                System.out.println(new String(message.getBody()));
            }

            System.out.println("batchSize: " + messages.size());
            int result = atomicInteger.addAndGet(messages.size());
            System.out.println("totalSize: " + result);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
    }
}
