package com.github.wz2cool.demo.rocketmqdemo.model;


public class ProductOrder {

    private String orderId;
    private String type;

    public ProductOrder(String orderId, String type) {
        this.orderId = orderId;
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
