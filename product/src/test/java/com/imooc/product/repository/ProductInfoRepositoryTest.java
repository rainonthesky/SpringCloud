package com.imooc.product.repository;

import com.imooc.product.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest  {
    @Autowired
    private  ProductInfoRepository productInfoRepository;
    @Test
    public void findByStatus()throws Exception{
       List<ProductInfo> list= productInfoRepository.findByProductStatus(2);
       System.out.println(list);
        Assert.assertTrue(list.size()>0);
    }


    @Test
    public void findByProductIdIn() throws Exception{
        List<ProductInfo> list =productInfoRepository.findByProductIdIn(Arrays.asList("123456","123458"));
        Assert.assertTrue(list.size()>0);
    }
}