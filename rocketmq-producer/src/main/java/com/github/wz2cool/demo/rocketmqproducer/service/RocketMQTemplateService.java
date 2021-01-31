package com.github.wz2cool.demo.rocketmqproducer.service;

import org.apache.rocketmq.client.Validators;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageBatch;
import org.apache.rocketmq.common.message.MessageClientIDSetter;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
public class RocketMQTemplateService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private final Method createRocketMqMessageMethod;

    public RocketMQTemplateService() throws NoSuchMethodException {
        createRocketMqMessageMethod = RocketMQTemplate.class.getDeclaredMethod("createRocketMqMessage", String.class, Message.class);
        createRocketMqMessageMethod.setAccessible(true);
    }

    public <T extends Message> SendResult syncSendOrderly(String destination, Collection<T> messages, String hashKey, long timeout) {
        if (Objects.isNull(messages) || messages.isEmpty()) {
            throw new IllegalArgumentException("`messages` can not be empty");
        }

        try {
            Collection<org.apache.rocketmq.common.message.Message> rmqMsgs = new ArrayList<>();
            for (T message : messages) {
                if (Objects.isNull(message)) {
                    continue;
                }
                rmqMsgs.add(this.createRocketMqMessage(destination, message));
            }
            MessageBatch messageBatch = batch(rmqMsgs);
            return rocketMQTemplate.getProducer().send(messageBatch, rocketMQTemplate.getMessageQueueSelector(), hashKey, timeout);
        } catch (Exception e) {
            throw new MessagingException(e.getMessage(), e);
        }
    }

    private MessageBatch batch(Collection<org.apache.rocketmq.common.message.Message> msgs) throws MQClientException {
        MessageBatch msgBatch;
        try {
            msgBatch = MessageBatch.generateFromList(msgs);
            for (org.apache.rocketmq.common.message.Message message : msgBatch) {
                Validators.checkMessage(message, rocketMQTemplate.getProducer());
                MessageClientIDSetter.setUniqID(message);
                message.setTopic(rocketMQTemplate.getProducer().withNamespace(message.getTopic()));
            }
            msgBatch.setBody(msgBatch.encode());
        } catch (Exception e) {
            throw new MQClientException("Failed to initiate the MessageBatch", e);
        }
        msgBatch.setTopic(rocketMQTemplate.getProducer().withNamespace(msgBatch.getTopic()));
        return msgBatch;
    }

    private org.apache.rocketmq.common.message.Message createRocketMqMessage(
            String destination, Message<?> message) {
        try {
            return (org.apache.rocketmq.common.message.Message) createRocketMqMessageMethod
                    .invoke(this.rocketMQTemplate, destination, message);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


}
