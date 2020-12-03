package com.github.wz2cool.demo.rocketmqproducer.service;

import com.github.wz2cool.demo.rocketmqproducer.model.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final int FACTORY_COUNT = 10;

    @Resource
    private ProductProducer productProducer;

    public List<Product> generateProductAndSend(int count) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Product product = new Product();
            product.setProductId((long) i);
            product.setProductName("Product_" + i % FACTORY_COUNT);
            product.setCreateTime(new Timestamp(System.currentTimeMillis()));
            productList.add(product);
        }
        productProducer.sendProducts(productList.toArray(new Product[0]));
        return productList;

    }
}
