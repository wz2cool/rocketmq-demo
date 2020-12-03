package com.github.wz2cool.demo.rocketmqproducer.model;

import java.sql.Timestamp;

public class Product {

    /**
     * 十个工厂生产产品，1号产品到1号工厂，11 到1号
     */
    private Long productId;
    private String productName;
    private Timestamp createTime;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Timestamp getCreateTime() {
        return createTime == null ? null : new Timestamp(createTime.getTime());
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime == null ? null : new Timestamp(createTime.getTime());
    }
}
