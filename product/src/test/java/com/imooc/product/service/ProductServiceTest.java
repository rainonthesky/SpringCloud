package com.imooc.product.service;

import com.imooc.product.ProductApplicationTests;
import com.imooc.product.dto.CartDto;
import com.imooc.product.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@Component
public class ProductServiceTest extends ProductApplicationTests {
    @Autowired
    private  ProductService productService;
    @Test
    public void finUpAll() {
        List<ProductInfo>list= productService.finUpAll();
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void decreaseStock() {
        CartDto cartDto=new CartDto("123456",2);
        productService.decreaseStock(Arrays.asList(cartDto));



    }
}