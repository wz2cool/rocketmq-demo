package com.github.wz2cool.demo.rocketmqconsumer.model;

import java.sql.Timestamp;

public class Product {

    /**
     * 十个工厂生产产品，1号产品到1号工厂，11 到1号
     */
    private Long productId;
    private Integer factoryId;
    private Timestamp createTime;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Timestamp getCreateTime() {
        return createTime == null ? null : new Timestamp(createTime.getTime());
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime == null ? null : new Timestamp(createTime.getTime());
    }
}
