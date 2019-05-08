package com.imooc.order.client;

import com.imooc.product.dto.CartDto;
import com.imooc.product.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="product")
public interface ProductClient {
    @GetMapping("/msg")
    String productMsg();

    @PostMapping(value = "/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);
    @PostMapping(value = "/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDto>cartDtoList);
}
