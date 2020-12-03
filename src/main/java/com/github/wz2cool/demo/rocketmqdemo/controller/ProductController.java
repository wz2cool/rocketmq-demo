package com.github.wz2cool.demo.rocketmqdemo.controller;

import com.github.wz2cool.demo.rocketmqdemo.model.ProductOrder;
import com.github.wz2cool.demo.rocketmqdemo.service.ProducerService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Resource
    private ProducerService producerService;

    private static List<ProductOrder> orderList = null;
    private static String producerGroup = "test_producer";

    /**
     * 模拟数据
     */
    static {
        orderList = new ArrayList<>();
        orderList.add(new ProductOrder("XXX001", "订单创建"));
        orderList.add(new ProductOrder("XXX001", "订单付款"));
        orderList.add(new ProductOrder("XXX001", "订单完成"));
        orderList.add(new ProductOrder("XXX002", "订单创建"));
        orderList.add(new ProductOrder("XXX002", "订单付款"));
        orderList.add(new ProductOrder("XXX002", "订单完成"));
        orderList.add(new ProductOrder("XXX003", "订单创建"));
        orderList.add(new ProductOrder("XXX003", "订单付款"));
        orderList.add(new ProductOrder("XXX003", "订单完成"));
    }

    @GetMapping("message")
    public void sendMessage() throws Exception {
        producerService.sendProductMessage(orderList);
    }

}
