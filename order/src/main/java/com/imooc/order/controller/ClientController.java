package com.imooc.order.controller;

import com.imooc.order.client.ProductClient;
import com.imooc.product.dto.CartDto;
import com.imooc.product.entity.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {
    @Autowired
    private ProductClient productClient;
    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        String response=productClient.productMsg();
        log.info("response={}",response);
        return  response;
//        1.第一种方式
//        RestTemplate restTemplate=new RestTemplate();
//        String response=restTemplate.getForObject("http://localhost:8082/msg",String.class);
//        log.info("response={}",response);
//        return  response;
//        第二种方式（利用loadBaanceClient通过获取应用名获取url，然后在使用restTemplate)
//        第三种方式（@LoadBalance）
    }
    @GetMapping(value = "/getProductList")
    public String getProductList(){
        List<ProductInfo> productInfoList= productClient.listForOrder(Arrays.asList("123458"));
        log.info("response={}",productInfoList);
        return "ok";

    }

    @GetMapping(value = "/decreaseStock")
    public String decreaseStock(){
         productClient.decreaseStock(Arrays.asList(new CartDto("123456",4)));
        return "ok";

    }
}
