package com.imooc.order.repository;

import com.imooc.order.OrderApplicationTests;
import com.imooc.order.entity.OrderMaster;
import com.imooc.order.enums.OrderStatus;
import com.imooc.order.enums.PayStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Test
    public void testSave(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("12456");
        orderMaster.setBuyerName("chenyaping");
        orderMaster.setBuyerPhone("1733233");
        orderMaster.setBuyerAddress("上海");
        orderMaster.setBuyerOpenid("12121");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderMasterRepository.save(orderMaster);

    }


}