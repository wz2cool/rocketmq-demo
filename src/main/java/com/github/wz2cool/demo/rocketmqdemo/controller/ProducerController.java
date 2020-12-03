package com.github.wz2cool.demo.rocketmqdemo.controller;

import com.github.wz2cool.demo.rocketmqdemo.model.User;
import com.github.wz2cool.demo.rocketmqdemo.service.ProducerService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    @Resource
    ProducerService producerService;

    @PostMapping("/string")
    public SendResult sendString(@RequestBody String message) throws MQClientException {
        return producerService.sendString(message);
    }

    @PostMapping("/user")
    public SendResult sendUser(@RequestBody User user) {
        return producerService.sendUser(user);
    }

}
