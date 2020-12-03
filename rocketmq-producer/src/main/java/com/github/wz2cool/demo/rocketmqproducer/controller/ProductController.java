package com.github.wz2cool.demo.rocketmqproducer.controller;

import com.github.wz2cool.demo.rocketmqproducer.model.Product;
import com.github.wz2cool.demo.rocketmqproducer.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping()
    public List<Product> generateProducts(@RequestBody Integer count) {
        return productService.generateProductAndSend(count);
    }
}

