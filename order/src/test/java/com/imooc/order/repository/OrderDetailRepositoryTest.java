package com.imooc.order.repository;

import com.imooc.order.OrderApplicationTests;
import com.imooc.order.entity.OrderDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;
@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void testSave(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("12367");
        orderDetail.setOrderId("12456");
        orderDetail.setProductId("134567787");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductIcon("http://xxx.com");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductQuantity(2);
        orderDetail.setCreateTime(new Date());
        orderDetail.setUpdateTime(new Date());
        orderDetailRepository.save(orderDetail);
    }




}


























